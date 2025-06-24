package br.com.andervilo.anagrams.service;

import br.com.andervilo.anagrams.exception.AnagramGenerationException;
import br.com.andervilo.anagrams.exception.EmptyInputException;
import br.com.andervilo.anagrams.exception.InvalidCharacterException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AnagramService {

    public List<String> generateAnagrams(Set<Character> characters) {
        if (characters == null || characters.isEmpty()) {
            throw new EmptyInputException("Input characters cannot be null or empty");
        }

        validateCharacters(characters);

        try {
            StringBuilder sb = new StringBuilder();
            for (Character c : characters) {
                sb.append(c);
            }
            String letters = sb.toString();

            List<String> result = new ArrayList<>();
            generatePermutations("", letters, result);
            return result;
        } catch (Exception e) {
            throw new AnagramGenerationException("Error generating anagrams", e);
        }
    }

    private void validateCharacters(Set<Character> characters) {
        for (Character c : characters) {
            if (!Character.isLetter(c)) {
                throw new InvalidCharacterException("Input must contain only letters, found: " + c);
            }
        }
    }

    private void generatePermutations(String prefix, String remaining, List<String> result) {
        int n = remaining.length();
        if (n == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutations(prefix + remaining.charAt(i),
                                   remaining.substring(0, i) + remaining.substring(i + 1, n),
                                   result);
            }
        }
    }
}
