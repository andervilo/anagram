package br.com.andervilo.anagrams.exception;

public class AnagramGenerationException extends RuntimeException {
    public AnagramGenerationException(String message) {
        super(message);
    }
    
    public AnagramGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}