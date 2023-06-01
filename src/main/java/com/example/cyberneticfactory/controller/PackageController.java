package com.example.cyberneticfactory.controller;

import com.example.cyberneticfactory.controller.resources.PackageResource;
import com.example.cyberneticfactory.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/packages")
@RequiredArgsConstructor
public class PackageController {
    private final PackageService service;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PackageResource resource) {
        PackageResource saved = service.save(resource);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("api/v1/packages/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody PackageResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @PutMapping("/{id}/addProduct/{productId}")
    public ResponseEntity<?> addProduct(@PathVariable long id, @PathVariable long productId) {
        return ResponseEntity.ok(service.addProduct(id, productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
