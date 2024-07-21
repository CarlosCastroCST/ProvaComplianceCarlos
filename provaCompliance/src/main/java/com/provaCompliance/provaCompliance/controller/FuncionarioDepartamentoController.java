package com.provaCompliance.provaCompliance.controller;

import com.provaCompliance.provaCompliance.model.FuncionarioDepartamento;
import com.provaCompliance.provaCompliance.service.FuncionarioDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario-departamento")
public class FuncionarioDepartamentoController {
    @Autowired
    private FuncionarioDepartamentoService funcionarioDepartamentoService;

    @PostMapping
    public ResponseEntity<FuncionarioDepartamento> create(@RequestBody FuncionarioDepartamento funcionarioDepartamento) {
        return ResponseEntity.ok(funcionarioDepartamentoService.save(funcionarioDepartamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FuncionarioDepartamento>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(funcionarioDepartamentoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDepartamento>> getAll() {
        return ResponseEntity.ok(funcionarioDepartamentoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        funcionarioDepartamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
