package br.com.andervilo.anagrams.exception;

public class InvalidCharacterException extends RuntimeException {
    public InvalidCharacterException(String message) {
        super(message);
    }
}