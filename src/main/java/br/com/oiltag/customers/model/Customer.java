package br.com.oiltag.customers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(CustomerId.class)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id", unique = true, nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @JsonProperty(value = "customer_id")
    private UUID customerId;
    @Id
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    private String name;
    private String phone;
}
