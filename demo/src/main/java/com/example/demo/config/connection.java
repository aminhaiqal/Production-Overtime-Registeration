package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import com.example.demo.dbUtil.staffDAO;
import com.example.demo.dbUtil.overtimeDAO;

@Controller
public class connection {
    @Bean
    public staffDAO staffDAO() {
        return new staffDAO();
    }

    @Bean
    public overtimeDAO overtimeDAO() {
        return new overtimeDAO();
    }
}
