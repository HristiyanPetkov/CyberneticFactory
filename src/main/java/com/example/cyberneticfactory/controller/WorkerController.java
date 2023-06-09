package com.example.cyberneticfactory.controller;

import com.example.cyberneticfactory.controller.resources.WorkerResource;
import com.example.cyberneticfactory.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/workers")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService service;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody WorkerResource resource) {
        WorkerResource saved = service.save(resource);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("api/v1/workers/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody WorkerResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/audits")
    public ResponseEntity<?> getAudits() {
        return ResponseEntity.ok(service.getAudits());
    }

    @GetMapping("/{id}/audits")
    public ResponseEntity<?> getAuditsById(@PathVariable long id) {
        return ResponseEntity.ok(service.getAuditsById(id));
    }

    @GetMapping("/byDate")
    public ResponseEntity<?> getAuditsByDate(@RequestParam String date) {
        return ResponseEntity.ok(service.getAuditsByDate(date));
    }
}
