package com.provaCompliance.provaCompliance.controller;

import com.provaCompliance.provaCompliance.model.Funcionario;
import com.provaCompliance.provaCompliance.model.FuncionarioDepartamento;
import com.provaCompliance.provaCompliance.service.FuncionarioDepartamentoService;
import com.provaCompliance.provaCompliance.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioDepartamentoService funcionarioDepartamentoService;

    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario funcionario) {
        return ResponseEntity.ok(funcionarioService.save(funcionario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable UUID id, @RequestBody Funcionario funcionario) {
        Optional<Funcionario> existingFuncionario = funcionarioService.findById(id);
        if (existingFuncionario.isPresent()) {
            funcionario.setUuid(id);
            Funcionario updatedFuncionario = funcionarioService.save(funcionario);
            return ResponseEntity.ok(updatedFuncionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Funcionario>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(funcionarioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        return ResponseEntity.ok(funcionarioService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        funcionarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Funcionario>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(funcionarioService.findByNome(nome));
    }

    @GetMapping("/nome-funcao")
    public ResponseEntity<List<Funcionario>> getByNomeAndFuncao(@RequestParam String nome, @RequestParam String funcao) {
        return ResponseEntity.ok(funcionarioService.findByNomeAndFuncao(nome, funcao));
    }

    @GetMapping("/nome-departamento")
    public ResponseEntity<List<FuncionarioDepartamento>> getByNomeAndDepartamento(
            @RequestParam String nome, @RequestParam String departamento) {
        List<FuncionarioDepartamento> funcionariosDepartamentos = funcionarioDepartamentoService.findByFuncionarioNomeAndDepartamento(nome, departamento);
        return ResponseEntity.ok(funcionariosDepartamentos);
    }
}
