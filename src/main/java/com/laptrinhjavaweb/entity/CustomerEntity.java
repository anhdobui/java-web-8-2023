package com.laptrinhjavaweb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{

  @Column(name = "fullname")
  private String fullName;

  @Column
  private String phone;

  @Column
  private String email;

  @OneToMany(mappedBy = "customer")
  private List<AssignmentCustomerEntity> assignmentCustomers;

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

  public List<AssignmentCustomerEntity> getAssignmentCustomers() {
    return assignmentCustomers;
  }

  public void setAssignmentCustomers(
      List<AssignmentCustomerEntity> assignmentCustomers) {
    this.assignmentCustomers = assignmentCustomers;
  }

  public List<TransactionEntity> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<TransactionEntity> transactions) {
    this.transactions = transactions;
  }
}
