package br.com.oiltag.customers.entrypoint;

import br.com.oiltag.customers.entrypoint.dto.CustomerDTO;
import br.com.oiltag.customers.usecase.CustomerCrudOperations;
import br.com.oiltag.customers.usecase.impl.CustomerUsecaseImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@Tag(name = "Customer")
@RequestMapping("/api/v1")
public class FindCustomerController {

    private final CustomerCrudOperations customerService;

    public FindCustomerController(CustomerUsecaseImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = {"/customers"})
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<CustomerDTO> customerDTOList = customerService.findAll();
        return ResponseEntity.ok(customerDTOList);
    }

    @GetMapping(value = {"/customers/{uuid}", "/customers/"})
    public ResponseEntity<CustomerDTO> getById(@PathVariable("uuid") @NotNull UUID uuid) {
        CustomerDTO customer = customerService.getById(uuid);
        return ResponseEntity.ok(customer);
    }

}
