package com.provaCompliance.provaCompliance;

import com.provaCompliance.provaCompliance.controller.FuncionarioController;
import com.provaCompliance.provaCompliance.model.Funcionario;
import com.provaCompliance.provaCompliance.service.FuncionarioService;
import com.provaCompliance.provaCompliance.view.FuncionarioRepository;
import com.provaCompliance.provaCompliance.model.FuncionarioDepartamento;
import com.provaCompliance.provaCompliance.service.FuncionarioDepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FuncionarioServiceTest {

    private MockMvc mockMvc;

    private Funcionario funcionario;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioDepartamentoService funcionarioDepartamentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(funcionarioController).build();
    }

    @Test
    public void testSaveFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setEndereco("Rua A, 123");
        funcionario.setSalario(BigDecimal.valueOf(3000.00));
        funcionario.setDataContratacao(LocalDateTime.now());
        funcionario.setFuncao("Developer");

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario savedFuncionario = funcionarioService.save(funcionario);

        assertNotNull(savedFuncionario);
        assertEquals("João", savedFuncionario.getNome());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    public void testFindFuncionarioById() {
        UUID id = UUID.randomUUID();
        Funcionario funcionario = new Funcionario();
        funcionario.setUuid(id);

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

        Optional<Funcionario> foundFuncionario = funcionarioService.findById(id);

        assertTrue(foundFuncionario.isPresent());
        assertEquals(id, foundFuncionario.get().getUuid());
        verify(funcionarioRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteFuncionarioById() {
        UUID id = UUID.randomUUID();
        doNothing().when(funcionarioRepository).deleteById(id);

        funcionarioService.deleteById(id);

        verify(funcionarioRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindFuncionarioByNome() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");

        when(funcionarioRepository.findByNome("João")).thenReturn(Collections.singletonList(funcionario));

        List<Funcionario> foundFuncionarios = funcionarioService.findByNome("João");

        assertNotNull(foundFuncionarios);
        assertFalse(foundFuncionarios.isEmpty());
        assertEquals("João", foundFuncionarios.get(0).getNome());
        verify(funcionarioRepository, times(1)).findByNome("João");
    }

    @Test
    public void testFindFuncionarioByNomeAndFuncao() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setFuncao("Developer");

        when(funcionarioRepository.findByNomeAndFuncao("João", "Developer")).thenReturn(Collections.singletonList(funcionario));

        List<Funcionario> foundFuncionarios = funcionarioService.findByNomeAndFuncao("João", "Developer");

        assertNotNull(foundFuncionarios);
        assertFalse(foundFuncionarios.isEmpty());
        assertEquals("João", foundFuncionarios.get(0).getNome());
        assertEquals("Developer", foundFuncionarios.get(0).getFuncao());
        verify(funcionarioRepository, times(1)).findByNomeAndFuncao("João", "Developer");
    }

    @Test
    public void testGetByNomeAndDepartamento() throws Exception {
        FuncionarioDepartamento funcionarioDepartamento = new FuncionarioDepartamento();
        List<FuncionarioDepartamento> funcionarios = Collections.singletonList(funcionarioDepartamento);

        when(funcionarioDepartamentoService.findByFuncionarioNomeAndDepartamento("João", "TI")).thenReturn(funcionarios);

        mockMvc.perform(get("/funcionarios/nome-departamento")
                        .param("nome", "João")
                        .param("departamento", "TI")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());

        verify(funcionarioDepartamentoService, times(1)).findByFuncionarioNomeAndDepartamento("João", "TI");
    }



}
