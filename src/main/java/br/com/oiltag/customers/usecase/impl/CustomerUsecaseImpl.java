package br.com.oiltag.customers.usecase.impl;

import br.com.oiltag.customers.entrypoint.CustomerDTO;
import br.com.oiltag.customers.model.Customer;
import br.com.oiltag.customers.repository.CustomerRepository;
import br.com.oiltag.customers.usecase.CustomerCrudOperations;
import br.com.oiltag.customers.utils.mappers.CustomerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CustomerUsecaseImpl implements CustomerCrudOperations {
    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    public CustomerUsecaseImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::customerToCustomerDTO).toList();
    }

    @Override
    @Cacheable(cacheNames = "customers")
    public CustomerDTO getById(UUID uuid) {
        log.info("executando getById");
        Customer customer = customerRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        log.info("Criando um novo cliente.");
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public void update(CustomerDTO customerDTO) {
        log.info("Atualizando um cliente existente.");
        log.info("Customer {} updated. Id: {}", customerDTO.getName(), customerDTO.getCustomerId());
        customerRepository.updateNameByCustomerId(customerDTO.getName(), customerDTO.getCustomerId());
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Customer deleted. Id: {}", uuid);
        Customer customer = customerRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        customerRepository.delete(customer);
    }
}
