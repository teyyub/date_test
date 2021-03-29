package com.example.demo;


import java.time.LocalDate;

public class ResponseDate {
    private final LocalDate datePost;

    public ResponseDate(LocalDate datePost) {
        this.datePost = datePost;
    }

    public LocalDate getDatePost() {
        return datePost;
    }
}
