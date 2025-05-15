package com.example.API.controller;

import com.example.API.DTO.CreateWordDTO;
import com.example.API.model.Word;
import com.example.API.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/words")
public class WordController {
    @Autowired
    private WordService wordService;

    @GetMapping("/user/{userId}")
    public List<Word> getAllWordsByUser(@PathVariable Long userId) {
        return wordService.getAllWordsByUserId(userId);
    }

    @GetMapping("/{id}/user/{userId}")
    public ResponseEntity<?> getWordByIdAndUser(@PathVariable Integer id, @PathVariable Long userId) {
        return wordService.getWordByIdAndUserId(id, userId)
                .<ResponseEntity<?>>map(word -> ResponseEntity.ok(word))
                .orElse(ResponseEntity.status(404).body(Map.of(
                        "status", "error",
                        "message", "Word not found"
                )));
    }

    @PostMapping
    public ResponseEntity<?> createWord(@RequestBody CreateWordDTO dto) {
        try {
            Word created = wordService.createWord(dto);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Word created",
                    "word", created
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    @PutMapping("/{id}/user/{userId}")
    public ResponseEntity<?> updateWord(@PathVariable Integer id, 
                                      @PathVariable Long userId,
                                      @RequestBody Word word) {
        return wordService.updateWord(id, word, userId)
                .map(updated -> ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Word updated",
                        "word", updated
                )))
                .orElse(ResponseEntity.status(404).body(Map.of(
                        "status", "error",
                        "message", "Word not found"
                )));
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> deleteWord(@PathVariable Integer id, @PathVariable Long userId) {
        boolean deleted = wordService.deleteWord(id, userId);
        if (deleted) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Word deleted successfully"
            ));
        }
        return ResponseEntity.status(404).body(Map.of(
                "status", "error",
                "message", "Word not found"
        ));
    }
}