package br.com.oiltag.customers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@JsonTest
public class LearningMoreAboutJacksonTest {

    @Autowired
    ObjectMapper objectMapper;

    private CustomerTest customerTest;

    @BeforeEach
    public void setup() {
        customerTest = customerTest.builder()
                .uuid(UUID.randomUUID())
                .firstName("Thiago")
                .lastName(" dos Santos")
                .birthdate(OffsetDateTime.now())
                .age(39)
                .deathDate(LocalDate.now())
                .build();
    }

    @Test
    public void objectToString() throws JsonProcessingException {
        String serializedCustomer = objectMapper.writeValueAsString(customerTest);
        log.info(serializedCustomer);
    }
}
