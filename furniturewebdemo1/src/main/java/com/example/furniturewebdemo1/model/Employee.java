package com.example.furniturewebdemo1.model;

import com.example.furniturewebdemo1.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee")
@JsonIgnoreProperties({"invoiceDetails", "employee", "invoiceProducts", "receipts","employees"})
public class Employee extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long id;

    @Column(name = "full_name")
    private String fullname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private double salary;

    @Column(name = "bonus")
    private double bonus;

    //    @ManyToOne
//    @JoinColumn(name = "role_id")
//    @JsonIgnoreProperties("employees")
//    private Role role;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Collection<InvoiceProduct> invoiceProducts;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Collection<InvoiceDetail> invoiceDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Collection<Receipt> receipts;

    public Employee(String fullname, String email, String username, String password) {
        this.fullname=fullname;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
