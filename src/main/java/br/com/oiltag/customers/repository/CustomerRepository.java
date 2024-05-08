package br.com.oiltag.customers.repository;

import br.com.oiltag.customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Transactional
    @Modifying
    @Query("update Customer c set c.name = ?1 where c.customerId = ?2")
    void updateNameByCustomerId(String name, UUID customerId);
}
