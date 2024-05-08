package br.com.oiltag.customers.entrypoint;

import br.com.oiltag.customers.usecase.CustomerCrudOperations;
import br.com.oiltag.customers.usecase.impl.CustomerUsecaseImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@Tag(name = "Customer")
@RequestMapping("/api/v1")
public class DeleteCustomerController {

    private final CustomerCrudOperations customerService;

    public DeleteCustomerController(CustomerUsecaseImpl customerService) {
        this.customerService = customerService;
    }

    @DeleteMapping(value = {"/customers/{uuid}", "/customers/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") UUID uuid) {
        customerService.delete(uuid);
    }

}
