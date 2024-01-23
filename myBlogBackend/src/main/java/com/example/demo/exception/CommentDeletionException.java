package com.example.demo.exception;

public class CommentDeletionException extends RuntimeException {
    public CommentDeletionException(String message) {
        super(message);
    }
}