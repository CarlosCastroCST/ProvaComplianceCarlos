package com.provaCompliance.provaCompliance;

import com.provaCompliance.provaCompliance.model.FuncionarioDepartamento;
import com.provaCompliance.provaCompliance.service.FuncionarioDepartamentoService;
import com.provaCompliance.provaCompliance.view.FuncionarioDepartamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class FuncionarioDepartamentoServiceTest {
    @InjectMocks
    private FuncionarioDepartamentoService funcionarioDepartamentoService;

    @Mock
    private FuncionarioDepartamentoRepository funcionarioDepartamentoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByFuncionarioNomeAndDepartamento() {
        FuncionarioDepartamento funcionarioDepartamento = new FuncionarioDepartamento();

        when(funcionarioDepartamentoRepository.findByFuncionarioNomeAndDepartamentoDepartamento("João", "TI"))
                .thenReturn(Collections.singletonList(funcionarioDepartamento));

        List<FuncionarioDepartamento> result = funcionarioDepartamentoService.findByFuncionarioNomeAndDepartamento("João", "TI");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(funcionarioDepartamentoRepository, times(1)).findByFuncionarioNomeAndDepartamentoDepartamento("João", "TI");
    }
}
