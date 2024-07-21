package com.provaCompliance.provaCompliance.view;

import com.provaCompliance.provaCompliance.model.Departamento;
import com.provaCompliance.provaCompliance.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    List<Funcionario> findByNome(String nome);
    List<Funcionario> findByNomeAndFuncao(String nome, String funcao);
}
