package br.com.oiltag.customers.utils.mappers;

import br.com.oiltag.customers.entrypoint.dto.CustomerDTO;
import br.com.oiltag.customers.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
