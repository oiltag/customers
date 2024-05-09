package br.com.oiltag.customers.utils.mappers;

import br.com.oiltag.customers.entrypoint.CustomerDTO;
import br.com.oiltag.customers.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-09T16:37:14-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        customerDTO.customerId( customer.getCustomerId() );
        customerDTO.name( customer.getName() );

        return customerDTO.build();
    }

    @Override
    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerId( customerDTO.getCustomerId() );
        customer.setName( customerDTO.getName() );

        return customer;
    }
}
