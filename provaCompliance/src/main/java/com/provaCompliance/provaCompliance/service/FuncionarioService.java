package com.provaCompliance.provaCompliance.service;

import com.provaCompliance.provaCompliance.model.Funcionario;
import com.provaCompliance.provaCompliance.model.Departamento;
import com.provaCompliance.provaCompliance.model.FuncionarioDepartamento;
import com.provaCompliance.provaCompliance.view.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> findById(UUID id) {
        return funcionarioRepository.findById(id);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public void deleteById(UUID id) {
        funcionarioRepository.deleteById(id);
    }

    public List<Funcionario> findByNome(String nome) {
        return funcionarioRepository.findByNome(nome);
    }

    public List<Funcionario> findByNomeAndFuncao(String nome, String funcao) {
        return funcionarioRepository.findByNomeAndFuncao(nome, funcao);
    }
}
