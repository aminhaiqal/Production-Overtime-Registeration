package com.example.demo.config;

import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/*
 * _____________________________________________________________________________
 * |  • A class that creates database tables using the Spring JDBC template.
 * |  • This class uses the Spring `JdbcTemplate` to execute SQL queries and create
 * |    two tables: 'staff' and 'overtime'. If these tables do not already exist in
 * |    the database, they are created with the specified columns.
 * |  • The `createTable` method is called automatically when the Spring context is
 * |    refreshed, thanks to the `@EventListener` annotation.
 * |____________________________________________________________________________
 */

@Component
public class TableLoader {
    private final JdbcTemplate jdbcTemplate;

    public TableLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createTable();
    }

    public void createTable() {
        List<String> tables = jdbcTemplate.queryForList("SHOW TABLES", String.class);
        if (!tables.contains("staff")) {
            jdbcTemplate.execute("CREATE TABLE staff ("
                    + "staff_id VARCHAR(22) NOT NULL,"
                    + "name MEDIUMTEXT NOT NULL,"
                    + "dept VARCHAR(55),"
                    + "section VARCHAR(45) NOT NULL,"
                    + "PRIMARY KEY (staff_id)"
                    + ")");
            System.out.println("Table 'staff' created");
        }
        if (!tables.contains("overtime")) {
            jdbcTemplate.execute("CREATE TABLE overtime ("
                    + "staff_id VARCHAR(22) NOT NULL,"
                    + "MON BOOLEAN,"
                    + "TUE BOOLEAN,"
                    + "WED BOOLEAN,"
                    + "THU BOOLEAN,"
                    + "FRI BOOLEAN,"
                    + "SAT BOOLEAN,"
                    + "SUN BOOLEAN,"
                    + "PRIMARY KEY (staff_id),"
                    + "FOREIGN KEY (staff_id) REFERENCES staff(staff_id)"
                    + ")");
            System.out.println("Table 'overtime' created");
        }
    }
}
