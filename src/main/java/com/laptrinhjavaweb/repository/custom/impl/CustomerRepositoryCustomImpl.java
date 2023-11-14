package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long countCustomerFound(CustomerBuilder customerBuilder) {
        StringBuilder finalQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder("");
        StringBuilder whereQuery = new StringBuilder("");
        joinQuery.append(buildSqlJoining(customerBuilder));
        whereQuery.append(buildSqlWhere(customerBuilder));
        finalQuery.append("select count(*)").append("\nfrom customer as c").append(joinQuery)
                .append(SystemConstant.ONE_EQUAL_ONE).append(whereQuery);
        String sqlQuery = finalQuery.toString();
        Query query = entityManager.createNativeQuery(sqlQuery);
        Long count = ((Number) query.getSingleResult()).longValue();
        return count;

    }

    @Override
    public List<CustomerEntity> findByCustomer(CustomerBuilder customerBuilder, Pageable pageable) {
        Integer limmit = pageable.getPageSize();
        Integer ofSet = pageable.getPageNumber()*limmit;
        StringBuilder finalQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder("");
        StringBuilder whereQuery = new StringBuilder("");

        joinQuery.append(buildSqlJoining(customerBuilder));
        whereQuery.append(buildSqlWhere(customerBuilder));
        finalQuery.append("select c.*").append("\nfrom customer as c").append(joinQuery)
                    .append(SystemConstant.ONE_EQUAL_ONE).append(whereQuery)
                    .append("\n group by c.id")
                    .append("\n limit "+limmit)
                    .append("\n offset "+ofSet);
        String sqlQuery = finalQuery.toString();
        Query query = entityManager.createNativeQuery(sqlQuery, CustomerEntity.class);
        return query.getResultList();
    }

    private StringBuilder buildSqlWhere(CustomerBuilder customerBuilder) {
        StringBuilder whereSql = new StringBuilder("");
        try {
            Field[] fields = CustomerBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName().toLowerCase();
                Object objValue = field.get(customerBuilder);
                if (objValue != null) {
                    if (!fieldName.equals("staffid")) {
                        if (field.getType().getName().equals("java.lang.String") && !StringUtils.isNullOrEmpty(objValue.toString())) {
                            whereSql.append(" and c." + fieldName + " like '%" + objValue + "%'");
                        }
                    } else {
                        if(!objValue.toString().equals("-1")){
                            whereSql.append(" and ac.staffid = " + objValue);
                        }

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return whereSql;
    }

    private StringBuilder buildSqlJoining(CustomerBuilder customerBuilder) {
        StringBuilder sqlJoin = new StringBuilder("");

        if (customerBuilder.getStaffId() != null && customerBuilder.getStaffId() != -1) {
            sqlJoin.append(" inner join assignmentcustomer as ac on ac.customerid = c.id ");
        }
        return sqlJoin;
    }
}
