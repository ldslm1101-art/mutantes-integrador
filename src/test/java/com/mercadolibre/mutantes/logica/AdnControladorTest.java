package com.mercadolibre.mutantes.controlador;

import com.mercadolibre.mutantes.dto.AdnPedido;
import com.mercadolibre.mutantes.servicio.AdnServicio;
import com.mercadolibre.mutantes.servicio.EstadisticasDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AdnControlador.class)
class AdnControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AdnServicio adnServicio;


    @Test
    void testMutante_Retorna200() throws Exception {
        when(adnServicio.analizarAdn(any())).thenReturn(true);
        String json = "{\"dna\":[\"AAAA\",\"CCCC\",\"TCAG\",\"GGTC\"]}";

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testHumano_Retorna403() throws Exception {
        when(adnServicio.analizarAdn(any())).thenReturn(false);
        String json = "{\"dna\":[\"ATGC\",\"CAGT\",\"TTAT\",\"AGAC\"]}";

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());
    }

    @Test
    void testJsonInvalido_Retorna400() throws Exception {
        // Envia JSON vacío
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest()); // Asumiendo validación @NotNull en DTO o manual
    }

    @Test
    void testNullDna_Retorna400() throws Exception {
        // Envia "dna": null
        String json = "{\"dna\":null}";
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()); // O el error que maneje tu validación
    }

    @Test
    void testArrayVacio_Retorna400() throws Exception {
        String json = "{\"dna\":[]}";
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()); // O 200/403 según tu lógica de negocio
    }

    // --- GET /stats (3 Tests) ---

    @Test
    void testStats_Retorna200() throws Exception {
        EstadisticasDto stats = new EstadisticasDto(40, 100, 0.4);
        when(adnServicio.obtenerEstadisticas()).thenReturn(stats);

        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna").value(40))
                .andExpect(jsonPath("$.count_human_dna").value(100))
                .andExpect(jsonPath("$.ratio").value(0.4));
    }

    @Test
    void testStats_Vacio_Retorna200() throws Exception {
        EstadisticasDto stats = new EstadisticasDto(0, 0, 0.0);
        when(adnServicio.obtenerEstadisticas()).thenReturn(stats);

        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna").value(0));
    }

    @Test
    void testStats_SoloMutantes_Retorna200() throws Exception {
        EstadisticasDto stats = new EstadisticasDto(10, 0, 1.0); // Ratio manejado en servicio
        when(adnServicio.obtenerEstadisticas()).thenReturn(stats);

        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ratio").value(1.0));
    }
}