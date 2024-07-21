package com.provaCompliance.provaCompliance.view;

import com.provaCompliance.provaCompliance.model.FuncionarioDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface FuncionarioDepartamentoRepository extends JpaRepository<FuncionarioDepartamento, UUID> {
    List<FuncionarioDepartamento> findByFuncionarioNomeAndDepartamentoDepartamento(String nome, String departamento);
}
