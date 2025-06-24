package br.com.andervilo.anagrams.dto;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class AnagramRequestTest {

    @Test
    void testGetLettersWithValidInput() {
        AnagramRequest request = new AnagramRequest();
        Set<Character> letters = new HashSet<>();
        letters.add('a');
        letters.add('b');
        letters.add('c');
        request.setLetters(letters);

        Set<Character> result = request.getLetters();
        assertEquals(3, result.size());
        assertTrue(result.contains('a'));
        assertTrue(result.contains('b'));
        assertTrue(result.contains('c'));
    }

    @Test
    void testGetLettersWithNullInput() {
        AnagramRequest request = new AnagramRequest();
        request.setLetters(null);
        assertTrue(request.getLetters().isEmpty());
    }

}
