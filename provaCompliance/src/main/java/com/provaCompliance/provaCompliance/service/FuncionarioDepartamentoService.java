package com.provaCompliance.provaCompliance.service;

import com.provaCompliance.provaCompliance.model.Departamento;
import com.provaCompliance.provaCompliance.model.Funcionario;
import com.provaCompliance.provaCompliance.model.FuncionarioDepartamento;
import com.provaCompliance.provaCompliance.view.FuncionarioDepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioDepartamentoService {
    @Autowired
    private FuncionarioDepartamentoRepository funcionarioDepartamentoRepository;

    public FuncionarioDepartamento save(FuncionarioDepartamento funcionarioDepartamento) {
        return funcionarioDepartamentoRepository.save(funcionarioDepartamento);
    }

    public Optional<FuncionarioDepartamento> findById(UUID id) {
        return funcionarioDepartamentoRepository.findById(id);
    }

    public List<FuncionarioDepartamento> findAll() {
        return funcionarioDepartamentoRepository.findAll();
    }

    public void deleteById(UUID id) {
        funcionarioDepartamentoRepository.deleteById(id);
    }

    public List<FuncionarioDepartamento> findByFuncionarioNomeAndDepartamento(String nome, String departamento) {
        return funcionarioDepartamentoRepository.findByFuncionarioNomeAndDepartamentoDepartamento(nome, departamento);
    }


}
