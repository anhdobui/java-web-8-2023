package com.laptrinhjavaweb.entity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{

    @Column(name = "fullname")
    private String fullName;

    @Column
    private String phone;

    @Column
    private String email;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid"),
            inverseJoinColumns = @JoinColumn(name = "staffid"))
    private List<UserEntity> staffs;

    @OneToMany(mappedBy = "customer")
    private List<TransactionEntity> transactions;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserEntity> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<UserEntity> staffs) {
        this.staffs = staffs;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}