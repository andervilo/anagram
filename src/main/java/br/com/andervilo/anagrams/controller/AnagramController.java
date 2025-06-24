package br.com.andervilo.anagrams.controller;

import br.com.andervilo.anagrams.dto.AnagramRequest;
import br.com.andervilo.anagrams.dto.AnagramResponse;
import br.com.andervilo.anagrams.service.AnagramService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/anagrams")
public class AnagramController {

    private final AnagramService anagramService;

    public AnagramController(AnagramService anagramService) {
        this.anagramService = anagramService;
    }

    @PostMapping
    public ResponseEntity<AnagramResponse> generateAnagrams(@Valid @RequestBody AnagramRequest request) {
        List<String> anagrams = anagramService.generateAnagrams(request.getLetters());
        return ResponseEntity.ok(new AnagramResponse(anagrams));
    }
}
