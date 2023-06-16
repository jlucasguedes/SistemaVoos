package br.com.jlucasguedes;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.jlucasguedes.domain.Voo;
import br.com.jlucasguedes.resources.VooResources;
import br.com.jlucasguedes.services.VoosService;

@WebMvcTest(VooResources.class)
public class VooResourcesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoosService voosService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() throws Exception {
        List<Voo> voos = new ArrayList<>();
        // Adicione voos à lista de voos

        when(voosService.listar()).thenReturn(voos);

        mockMvc.perform(MockMvcRequestBuilders.get("/voos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                // Adicione asserções adicionais conforme necessário
                .andReturn();

        verify(voosService, times(1)).listar();
    }
}
