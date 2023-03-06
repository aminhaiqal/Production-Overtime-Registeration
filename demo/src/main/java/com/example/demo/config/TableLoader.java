package com.example.demo.config;

import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
                    + "staff_id INT NOT NULL,"
                    + "name MEDIUMTEXT NOT NULL,"
                    + "dept VARCHAR(55),"
                    + "position VARCHAR(55),"
                    + "section VARCHAR(45) NOT NULL,"
                    + "line VARCHAR(45),"
                    + "date_joined DATE NOT NULL,"
                    + "PRIMARY KEY (staff_id)"
                    + ")");
            System.out.println("Table 'staff' created");
        }
        if (!tables.contains("overtime")) {
            jdbcTemplate.execute("CREATE TABLE overtime ("
                    + "staff_id INT NOT NULL,"
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
