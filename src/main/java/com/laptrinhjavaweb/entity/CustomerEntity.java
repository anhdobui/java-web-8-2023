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

    @Column
    private String company;

    @Column
    private String desire;

    @Column
    private String note;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
        @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid"),
            inverseJoinColumns = @JoinColumn(name = "staffid"))
    private List<UserEntity> staffs;

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesire() {
        return desire;
    }

    public void setDesire(String desire) {
        this.desire = desire;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}