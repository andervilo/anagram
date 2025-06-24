package br.com.andervilo.anagrams.service;

import br.com.andervilo.anagrams.exception.EmptyInputException;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class AnagramServiceTest {

    private final AnagramService anagramService = new AnagramService();

    @Test
    void testGenerateAnagramsWithThreeLetters() {
        Set<Character> letters = new HashSet<>();
        letters.add('a');
        letters.add('b');
        letters.add('c');

        List<String> result = anagramService.generateAnagrams(letters);
        assertEquals(6, result.size());
        assertTrue(result.containsAll(Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba")));
    }

    @Test
    void testGenerateAnagramsWithSingleLetter() {
        Set<Character> letters = new HashSet<>();
        letters.add('a');

        List<String> result = anagramService.generateAnagrams(letters);
        assertEquals(1, result.size());
        assertTrue(result.contains("a"));
    }

    @Test
    void testGenerateAnagramsWithEmptyInput() {
        Exception exception = assertThrows(EmptyInputException.class, () -> {
            anagramService.generateAnagrams(new HashSet<>());
        });

        String expectedMessage = "Input characters cannot be null or empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGenerateAnagramsWithNullInput() {
        Exception exception = assertThrows(EmptyInputException.class, () -> {
            anagramService.generateAnagrams(null);
        });

        String expectedMessage = "Input characters cannot be null or empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
