package com.example.furniturewebdemo1.model;
import com.example.furniturewebdemo1.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@JsonIgnoreProperties({"customer","invoiceDetails","invoiceProducts"})
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "discount")
    private double discount;


    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;


    @ManyToOne
    @JoinColumn(name = "customertype_id")
    @JsonIgnoreProperties("customers")
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Collection<InvoiceProduct> invoiceProducts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Collection<InvoiceDetail> invoiceDetails;

}
