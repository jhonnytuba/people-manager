package br.com.jhonatanserafim.peoplemanager.core.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/source", produces = APPLICATION_JSON_VALUE)
public class SourceController {

    private final String gitHubUrl;

    public SourceController(Environment environment) {
        this.gitHubUrl = environment.getProperty("github.url");
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getSource() {
        return ResponseEntity.ok(Map.of("url", gitHubUrl));
    }
}
