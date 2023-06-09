package com.example.cyberneticfactory.controller;

import com.example.cyberneticfactory.controller.resources.PartResource;
import com.example.cyberneticfactory.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/parts")
@RequiredArgsConstructor
public class PartController {
    private final PartService service;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PartResource resource) {
        PartResource saved = service.save(resource);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("api/v1/parts/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody PartResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
