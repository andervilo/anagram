package br.com.andervilo.anagrams.controller;

import br.com.andervilo.anagrams.dto.AnagramRequest;
import br.com.andervilo.anagrams.dto.AnagramResponse;
import br.com.andervilo.anagrams.exception.EmptyInputException;
import br.com.andervilo.anagrams.exception.InvalidCharacterException;
import br.com.andervilo.anagrams.service.AnagramService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnagramController.class)
class AnagramControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnagramService anagramService;

    @Test
    void testGenerateAnagramsWithValidInput() throws Exception {
        // Arrange
        List<String> mockAnagrams = Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba");
        when(anagramService.generateAnagrams(any(Set.class))).thenReturn(mockAnagrams);

        // Act & Assert
        mockMvc.perform(post("/api/v1/anagrams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"letters\":[\"a\",\"b\",\"c\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.anagrams").isArray())
                .andExpect(jsonPath("$.anagrams.length()").value(6))
                .andExpect(jsonPath("$.anagrams[0]").value("abc"))
                .andExpect(jsonPath("$.anagrams[1]").value("acb"))
                .andExpect(jsonPath("$.anagrams[2]").value("bac"))
                .andExpect(jsonPath("$.anagrams[3]").value("bca"))
                .andExpect(jsonPath("$.anagrams[4]").value("cab"))
                .andExpect(jsonPath("$.anagrams[5]").value("cba"));
    }

    @Test
    void testGenerateAnagramsWithInvalidInput() throws Exception {
        // Arrange
        when(anagramService.generateAnagrams(any(Set.class)))
                .thenThrow(new InvalidCharacterException("Input must contain only letters, found: 1"));

        // Act & Assert
        mockMvc.perform(post("/api/v1/anagrams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"letters\":[\"a\",\"b\",\"1\"]}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGenerateAnagramsWithEmptyInput() throws Exception {
        // Arrange
        when(anagramService.generateAnagrams(any(Set.class)))
                .thenThrow(new EmptyInputException("Input characters cannot be null or empty"));

        // Act & Assert
        mockMvc.perform(post("/api/v1/anagrams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"letters\":[]}"))
                .andExpect(status().isBadRequest());
    }
}
