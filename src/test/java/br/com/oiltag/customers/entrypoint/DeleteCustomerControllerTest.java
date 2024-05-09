package br.com.oiltag.customers.entrypoint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteCustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve deletar o custumer com sucesso.")
    public void deleteCustomerTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/customers/{uuid}", "725dbb58-71ab-456b-8684-aded4cb32bbf")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar cliente não encontrado.")
    public void cannotFindClientTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/customers/{uuid}", "6aa7a255-1c72-454b-83b1-e5e20cebf616")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar BadRequest se URI Variable ausente.")
    public void missingUriVariableTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/customers/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}