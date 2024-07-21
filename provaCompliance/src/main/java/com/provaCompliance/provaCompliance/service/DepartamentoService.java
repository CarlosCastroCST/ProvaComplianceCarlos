package com.provaCompliance.provaCompliance.service;

import com.provaCompliance.provaCompliance.model.Departamento;
import com.provaCompliance.provaCompliance.view.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento save(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Optional<Departamento> findById(UUID id) {
        return departamentoRepository.findById(id);
    }

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    public void deleteById(UUID id) {
        departamentoRepository.deleteById(id);
    }
}
