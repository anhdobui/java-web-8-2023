package com.laptrinhjavaweb.builder;

public class CustomerBuilder {


    private String fullname;
    private String email;
    private String phone;
    private Long staffId;

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Long getStaffId() {
        return staffId;
    }

    private CustomerBuilder(Builder builder) {
        this.fullname = builder.fullname;
        this.email = builder.email;
        this.phone = builder.phone;
        this.staffId = builder.staffId;
    }

    public static class Builder{
        private String fullname;
        private String email;
        private String phone;
        private Long staffId;

        public Builder setFullName(String fullName){
            this.fullname = fullName;
            return this;
        }
        public Builder setEmail(String email){
            this.email = email;
            return this;
        }
        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder setStaffId(Long staffId){
            this.staffId = staffId;
            return this;
        }
        public CustomerBuilder build(){
            return  new CustomerBuilder(this);
        }
    }
}
