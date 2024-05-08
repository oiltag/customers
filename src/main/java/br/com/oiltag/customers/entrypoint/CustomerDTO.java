package br.com.oiltag.customers.entrypoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @JsonProperty(value = "id")
    private UUID customerId;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;
}
