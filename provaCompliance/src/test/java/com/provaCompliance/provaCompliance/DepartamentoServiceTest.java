package com.provaCompliance.provaCompliance;

import com.provaCompliance.provaCompliance.model.Departamento;
import com.provaCompliance.provaCompliance.view.DepartamentoRepository;
import com.provaCompliance.provaCompliance.service.DepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartamentoServiceTest {
    @InjectMocks
    private DepartamentoService departamentoService;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveDepartamento() {
        Departamento departamento = new Departamento();
        departamento.setDepartamento("TI");
        departamento.setQtdeFuncionarios(10);

        when(departamentoRepository.save(any(Departamento.class))).thenReturn(departamento);

        Departamento savedDepartamento = departamentoService.save(departamento);

        assertNotNull(savedDepartamento);
        assertEquals("TI", savedDepartamento.getDepartamento());
        verify(departamentoRepository, times(1)).save(departamento);
    }

    @Test
    public void testFindDepartamentoById() {
        UUID id = UUID.randomUUID();
        Departamento departamento = new Departamento();
        departamento.setUuid(id);

        when(departamentoRepository.findById(id)).thenReturn(Optional.of(departamento));

        Optional<Departamento> foundDepartamento = departamentoService.findById(id);

        assertTrue(foundDepartamento.isPresent());
        assertEquals(id, foundDepartamento.get().getUuid());
        verify(departamentoRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteDepartamentoById() {
        UUID id = UUID.randomUUID();
        doNothing().when(departamentoRepository).deleteById(id);

        departamentoService.deleteById(id);

        verify(departamentoRepository, times(1)).deleteById(id);
    }
}
