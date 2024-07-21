package com.provaCompliance.provaCompliance.view;

import com.provaCompliance.provaCompliance.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {
}
