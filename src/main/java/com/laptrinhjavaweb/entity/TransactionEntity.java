package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity extends  BaseEntity{

  @Column
  private String code;
  @Column
  private String node;

  @ManyToOne
  @JoinColumn(name = "customerid")
  private CustomerEntity customer;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public CustomerEntity getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }
}
