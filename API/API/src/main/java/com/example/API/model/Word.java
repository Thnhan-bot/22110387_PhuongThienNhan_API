package com.example.API.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Word implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ten;        // Tên từ
    private String mota;       // Mô tả
    private String theLoai;    // Thể loại

    @Lob
    private byte[] image;      // Hình ảnh

    @Lob
    private byte[] sound;      // Âm thanh

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;         // User who created this word
}
