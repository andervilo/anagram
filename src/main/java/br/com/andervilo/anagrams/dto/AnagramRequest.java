package br.com.andervilo.anagrams.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class AnagramRequest {

    @NotNull(message = "Input cannot be null.")
    @NotEmpty(message = "Input cannot be empty.")
    private Set<Character> letters;

    public Set<Character> getLetters() {
        return letters != null ? letters : new HashSet<>();
    }

    public void setLetters(Set<Character> letters) {
        this.letters = letters;
    }

}
