package br.com.andervilo.anagrams.dto;

import java.util.List;

public class AnagramResponse {

    private List<String> anagrams;

    public AnagramResponse(List<String> anagrams) {
        this.anagrams = anagrams;
    }

    public List<String> getAnagrams() {
        return anagrams;
    }

    public void setAnagrams(List<String> anagrams) {
        this.anagrams = anagrams;
    }
}
