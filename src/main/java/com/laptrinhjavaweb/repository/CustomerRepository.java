package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long>, CustomerRepositoryCustom {
    Long countByIdIn(List<Long> ids);
    Long deleteByIdIn(List<Long> ids);
    boolean existsByIdAndStaffs_Id(Long id, Long staffid);
}
