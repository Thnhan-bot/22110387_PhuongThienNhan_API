package com.example.API.service;

import com.example.API.DTO.CreateWordDTO;
import com.example.API.model.User;
import com.example.API.model.Word;
import com.example.API.repository.UserRepository;
import com.example.API.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Word> getAllWordsByUserId(Long userId) {
        return wordRepository.findByUserId(userId);
    }

    public Optional<Word> getWordByIdAndUserId(Integer id, Long userId) {
        return wordRepository.findByIdAndUserId(id, userId);
    }

    public Word createWord(CreateWordDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Word word = new Word();
        word.setTen(dto.getTen());
        word.setMota(dto.getMota());
        word.setTheLoai(dto.getTheLoai());
        word.setImage(dto.getImage());
        word.setSound(dto.getSound());
        word.setUser(user);
        return wordRepository.save(word);
    }

    public Optional<Word> updateWord(Integer id, Word updatedWord, Long userId) {
        return wordRepository.findByIdAndUserId(id, userId).map(word -> {
            word.setTen(updatedWord.getTen());
            word.setMota(updatedWord.getMota());
            word.setTheLoai(updatedWord.getTheLoai());
            word.setImage(updatedWord.getImage());
            word.setSound(updatedWord.getSound());
            return wordRepository.save(word);
        });
    }

    public boolean deleteWord(Integer id, Long userId) {
        Optional<Word> word = wordRepository.findByIdAndUserId(id, userId);
        if (word.isPresent()) {
            wordRepository.deleteById(id);
            return true;
        }
        return false;
    }
}