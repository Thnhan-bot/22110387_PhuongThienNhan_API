package com.example.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWordDTO {
    private String ten;
    private String mota;
    private String theLoai;
    private byte[] image;
    private byte[] sound;
    private Long userId;  // ID of the user creating the word
}