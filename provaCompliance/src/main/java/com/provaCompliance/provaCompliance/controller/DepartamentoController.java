package com.provaCompliance.provaCompliance.controller;

import com.provaCompliance.provaCompliance.model.Departamento;
import com.provaCompliance.provaCompliance.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping
    public ResponseEntity<Departamento> create(@RequestBody Departamento departamento) {
        return ResponseEntity.ok(departamentoService.save(departamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable UUID id, @RequestBody Departamento departamento) {
        Optional<Departamento> existingDepartamento = departamentoService.findById(id);
        if (existingDepartamento.isPresent()) {
            departamento.setUuid(id);
            Departamento updatedDepartamento = departamentoService.save(departamento);
            return ResponseEntity.ok(updatedDepartamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Departamento>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(departamentoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> getAll() {
        return ResponseEntity.ok(departamentoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        departamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
