package br.com.oiltag.customers.json;

import br.com.oiltag.customers.utils.serializers.JsonSerializerExample;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTest {
    @JsonProperty(value = "id")
    private UUID uuid;

    @NotNull
    @Size(min = 3, max = 20)
    @JsonProperty("nome")
    private String firstName;

    @Size(min = 3, max = 50)
    @JsonProperty("sobrenome")
    private String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime birthdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer age;

    @JsonSerialize(using = JsonSerializerExample.class)
    private LocalDate deathDate;
}
