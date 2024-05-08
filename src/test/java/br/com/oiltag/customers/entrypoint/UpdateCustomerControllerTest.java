package br.com.oiltag.customers.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class UpdateCustomerControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Cliente deve ser atualizado com sucesso.")
    public void updateCustomerTest() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(UUID.randomUUID(), "Thiago dos Santos");
        String requestBody = objectMapper.writeValueAsString(customerDTO);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/customers/{uuid}", UUID.randomUUID().toString())
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar BadRequest se URI Variable ausente.")
    public void missingUriVariableTest() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(UUID.randomUUID(), "Thiago dos Santos");
        String requestBody = objectMapper.writeValueAsString(customerDTO);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/customers/")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Nome deve ser conter ao menos 3 caracteres.")
    public void badRequestNomePequenoTest() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().name("Th").build();
        String requestBody = objectMapper.writeValueAsString(customerDTO);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/customers/{uuid}", UUID.randomUUID().toString())
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Nome deve ser conter no máximo 100 caracteres.")
    public void badRequestNomeGrandeTest() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("Kubishka Valeriya Kasyanovna (Кубышка Валерия Касьяновна) Kubishka Valeriya Kasyanovna (Кубышка Валерия Касьяновна)")
                .build();
        String requestBody = objectMapper.writeValueAsString(customerDTO);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/customers/{uuid}", UUID.randomUUID().toString())
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}