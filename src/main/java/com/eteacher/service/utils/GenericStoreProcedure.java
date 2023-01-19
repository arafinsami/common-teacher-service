package com.eteacher.service.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenericStoreProcedure {

    private final JdbcTemplate jdbcTemplate;

    public SimpleJdbcCall callProcedure(String procedureName) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procedureName);
        return simpleJdbcCall;
    }

}
