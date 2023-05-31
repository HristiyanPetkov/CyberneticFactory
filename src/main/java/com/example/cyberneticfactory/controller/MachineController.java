package com.example.cyberneticfactory.controller;

import com.example.cyberneticfactory.controller.resources.MachineResource;
import com.example.cyberneticfactory.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/machines")
@RequiredArgsConstructor
public class MachineController {

    private final MachineService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MachineResource resource) {
        MachineResource saved = service.save(resource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("api/v1/machines/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody MachineResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
