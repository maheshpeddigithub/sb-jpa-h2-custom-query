package com.ssil.sb.jpa.h2.custom.query.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 512, unique = true)
    @NotBlank(message = "Product name cannot be blank")
    @Length(min = 5, max = 512, message = "Product name must be between 5-512 characters")
    private String name;

    @Column
    private int number;

    @Column
    @Min(value = 10, message = "balance must be greater or equal to 10")
    @Max(value = 9999, message = "balance must be less than or equal to 9999")
    private int balance;

    @Column
    private boolean active;

    @Column
    private Date created;

}
