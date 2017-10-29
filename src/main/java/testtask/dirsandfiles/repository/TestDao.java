package testtask.dirsandfiles.repository;

import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import testtask.dirsandfiles.domain.Path;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import static testtask.dirsandfiles.repository.TestDao.ComparingType.*;
import static testtask.dirsandfiles.repository.SimpleSqlConditionBuilder.ComparingType.*;

public class TestDao {
    private static final String ORDER_ID = "order_id";
    private static final String ORDER_NAME = "order_name";
    private static final String SERVICE = "service";
    private static final String STATUS = "status";
    private static final String LIBRARY_ID = "library_id";
    private static final String CREATION_DATE = "creation_date";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Path> getOrders(OrderSearchCondition cond) {
//        StringBuilder sql = new StringBuilder("SELECT * FROM \"order\" ");
//        List<Object> args = new ArrayList<>();
//        if (cond.getId() != null) addCondition(ORDER_ID, EQUAL, cond.getId(), sql, args);
//        if (cond.getName() != null) addCondition(ORDER_NAME, ILIKE, cond.getName(), sql, args);
//        if (cond.getServiceType() != null) addCondition(SERVICE, EQUAL, cond.getServiceType().name(), sql, args);
//        if (cond.getStatusType() != null) addCondition(STATUS, EQUAL, cond.getStatusType().name(), sql, args);
//        if (cond.getLibrary() != null) addCondition(LIBRARY_ID, EQUAL, cond.getLibrary().getId(), sql, args);
//        if (cond.getStartDate() != null) addCondition(CREATION_DATE, MORE_OR_EQUAL, Timestamp.valueOf(cond.getStartDate().atStartOfDay()), sql, args);
//        if (cond.getStartDate() != null) addCondition(CREATION_DATE, LESS, Timestamp.valueOf(cond.getEndDate().plusDays(1).atStartOfDay()), sql, args);
//        cutLastAnd(sql);
//        sql.append("ORDER BY creation_date DESC LIMIT ? OFFSET ?");
//        args.add(cond.getLimit());
//        args.add(cond.getOffset());
//        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Path>(), args);
        List<Object> args = new ArrayList<>();
        SimpleSqlConditionBuilder builder = new SimpleSqlConditionBuilder(args);
        String sql = "SELECT * FROM \"order\" " + builder
                .addCondition(ORDER_ID, EQUAL, cond.getId())
                .and().addCondition(ORDER_NAME, ILIKE, cond.getName())
                .and().addCondition(SERVICE, EQUAL, cond.getServiceType() != null ? cond.getServiceType().name() : null)
                .and().addCondition(STATUS, EQUAL, cond.getStatusType() != null ? cond.getStatusType().name() : null)
                .and().addCondition(LIBRARY_ID, EQUAL, cond.getLibrary() != null ? cond.getLibrary().getId() : null)
                .and().addCondition(CREATION_DATE, MORE_OR_EQUAL, cond.getStartDate() != null ? Timestamp.valueOf(cond.getStartDate().atStartOfDay()) : null)
                .and().addCondition(CREATION_DATE, LESS, cond.getStartDate() != null ? Timestamp.valueOf(cond.getEndDate().plusDays(1).atStartOfDay()) : null)
                .build() + "ORDER BY creation_date DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Path>(), args);
    }

//    private void cutLastAnd(StringBuilder sql) {
//        if (sql.length() >= 4) {
//            if (sql.substring(sql.length() - 4).equals("AND ")) {
//                sql.delete(sql.length() - 4, sql.length());
//            }
//        }
//    }
//
//    private void addCondition(String columnName, ComparingType type, Object newArg, StringBuilder sql, List<Object> args) {
//        if (newArg != null) {
//            if (args.size() == 0) sql.append("WHERE ");
//            sql.append(String.format("%s%s? AND ", columnName, type.value));
//            args.add((type == ILIKE) ? "%" + newArg + "%" : newArg);
//        }
//    }
//
//    public enum ComparingType {
//        EQUAL("="),
//        MORE_OR_EQUAL(">="),
//        LESS("<"),
//        ILIKE(" ILIKE ");
//
//        private String value;
//
//        ComparingType(String value) {
//            this.value = value;
//        }
//    }


    @Data
    private class OrderSearchCondition {
        Long id;
        String name;
        OrderServiceType serviceType;
        OrderStatusType statusType;
        Library library;
        String string;
        LocalDate startDate;
        LocalDate endDate;
        int offset = 0;
        int limit = 50;
    }

    @Data
    class Library {
        Long id;
    }

    enum OrderStatusType {
        NEW,
        CREATED
    }

    enum OrderServiceType {
        BOOKING,
        DELIVERY
    }
}
