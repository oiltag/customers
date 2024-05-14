package br.com.oiltag.customers.entrypoint;

import br.com.oiltag.customers.entrypoint.dto.CustomerDTO;
import br.com.oiltag.customers.usecase.CustomerCrudOperations;
import br.com.oiltag.customers.usecase.impl.CustomerUsecaseImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@Tag(name = "Customer")
@RequestMapping("/api/v1")
public class UpdateCustomerController {

    private final CustomerCrudOperations customerService;

    public UpdateCustomerController(CustomerUsecaseImpl customerService) {
        this.customerService = customerService;
    }

    @PutMapping(value = {"/customers/{uuid}", "/customers/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable("uuid") UUID uuid) {
        customerDTO.setCustomerId(uuid);
        customerService.update(customerDTO);
    }

}
