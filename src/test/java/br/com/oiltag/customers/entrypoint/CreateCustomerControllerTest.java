package br.com.oiltag.customers.entrypoint;

import br.com.oiltag.customers.entrypoint.dto.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateCustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Cliente deve ser cadastrado com sucesso.")
    public void createCustomer() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .email("thisanti@hotmail.com")
                .name("Thiago dos Santos")
                .build();
        String requestBody = objectMapper.writeValueAsString(customerDTO);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/customers")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Nome deve conter ao menos 3 caracteres.")
    public void badRequestNomePequenoTest() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().name("Th").build();
        String requestBody = objectMapper.writeValueAsString(customerDTO);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/customers")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Nome deve conter no máximo 100 caracteres.")
    public void badRequestNomeGrandeTest() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("Kubishka Valeriya Kasyanovna (Кубышка Валерия Касьяновна) Kubishka Valeriya Kasyanovna (Кубышка Валерия Касьяновна)")
                .build();
        String requestBody = objectMapper.writeValueAsString(customerDTO);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/customers")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

}