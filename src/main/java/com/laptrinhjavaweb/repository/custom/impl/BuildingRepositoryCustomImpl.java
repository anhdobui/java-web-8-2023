package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long countBuildingFound(BuildingSearchBuilder builder) {
        StringBuilder finalQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder("");
        StringBuilder whereQuery = new StringBuilder("");
        joinQuery.append(buildSqlJoining(builder));
        whereQuery.append(buildWhereCommonSql(builder)).append(builWhereSpecialSql(builder));
        finalQuery.append("select count(*)").append("\nfrom building as b").append(joinQuery)
                .append(SystemConstant.ONE_EQUAL_ONE).append(whereQuery);
        String sqlQuery = finalQuery.toString();
        Query query = entityManager.createNativeQuery(sqlQuery);
        Long count = ((Number) query.getSingleResult()).longValue();
        return count;

    }

    @Override
    public List<BuildingEntity> findBuilding(BuildingSearchBuilder builder, Pageable pageable) {

        Integer limmit = pageable.getPageSize();
        Integer ofSet = pageable.getPageNumber()*limmit;
        StringBuilder finalQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder("");
        StringBuilder whereQuery = new StringBuilder("");

        joinQuery.append(buildSqlJoining(builder));
        whereQuery.append(buildWhereCommonSql(builder)).append(builWhereSpecialSql(builder));
        finalQuery.append("select b.*").append("\nfrom building as b").append(joinQuery)
                .append(SystemConstant.ONE_EQUAL_ONE).append(whereQuery)
                .append("\n limit "+limmit)
                .append("\n offset "+ofSet);
//                .append("\n group by b.id");
        String sqlQuery = finalQuery.toString();
        Query query = entityManager.createNativeQuery(sqlQuery, BuildingEntity.class);
        return query.getResultList();
    }

    private StringBuilder buildSqlJoining(BuildingSearchBuilder builder) {
        StringBuilder sqlJoin = new StringBuilder("");
        if (builder.getStaffId() != null && builder.getStaffId() != -1) {
            sqlJoin.append(" inner join assignmentbuilding as ab on ab.buildingid=b.id ");
        }
        return sqlJoin;
    }

    private StringBuilder buildWhereCommonSql(BuildingSearchBuilder builder) {
        StringBuilder whereCommonSql = new StringBuilder("");
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName().toLowerCase();
                if (!fieldName.equals("staffid") && !fieldName.equals("districtcode") && !fieldName.equals("types")
                        && !fieldName.startsWith("rentarea") && !fieldName.startsWith("costrent")) {
                    Object objValue = field.get(builder);
                    if (objValue != null) {
                        if (field.getType().getName().equals("java.lang.String") && !StringUtils.isNullOrEmpty(objValue.toString())) {
                            whereCommonSql.append(" and b." + fieldName + " like '%" + objValue + "%'");
                        } else if (field.getType().getName().equals("java.lang.Integer")) {
                            whereCommonSql.append(" and b." + fieldName + " = " + objValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return whereCommonSql;
    }

    private StringBuilder builWhereSpecialSql(BuildingSearchBuilder builder) {
        StringBuilder whereSpecialSql = new StringBuilder("");
        if (builder.getStaffId() != null && builder.getStaffId() != -1) {
            whereSpecialSql.append(" and ab.staffid =" + builder.getStaffId());
        }
        if (builder.getDistrictCode() != null && !builder.getDistrictCode().equals("-1")){
            whereSpecialSql.append(" and b.district = '"+builder.getDistrictCode()+"'");
        }
        if (builder.getTypes() != null && builder.getTypes().size() > 0) {
            String whereTypes = builder.getTypes().stream().map(item -> " b.type like '%"+item+"%'").collect(Collectors.joining(" or"));
            whereSpecialSql.append(" and (")
                            .append(whereTypes)
                            .append(")");
        }
        if (builder.getCostRentFrom() != null) {
            whereSpecialSql.append(" and b.rentprice >= " + builder.getCostRentFrom());
        }
        if (builder.getCostRentTo() != null) {
            whereSpecialSql.append(" and b.rentprice <= " + builder.getCostRentTo());
        }
        if (builder.getRentAreaFrom() != null || builder.getRentAreaTo() != null) {
            whereSpecialSql.append(" and EXISTS (select * from rentarea as ra WHERE ra.buildingid = b.id");
            if (builder.getRentAreaFrom() != null) {
                whereSpecialSql.append(" and ra.value >= " + builder.getRentAreaFrom());
            }
            if (builder.getRentAreaTo() != null) {
                whereSpecialSql.append(" and ra.value <= " + builder.getRentAreaTo());
            }
            whereSpecialSql.append(")");
        }
        return whereSpecialSql;
    }
}
