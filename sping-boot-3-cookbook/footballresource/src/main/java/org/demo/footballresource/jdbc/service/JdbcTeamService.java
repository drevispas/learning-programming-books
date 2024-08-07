package org.demo.footballresource.jdbc.service;

import lombok.RequiredArgsConstructor;
import org.demo.footballresource.jdbc.entity.JdbcTeam;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JdbcTeamService {

    // JPA injects the JdbcTemplate bean
    private final JdbcTemplate jdbcTemplate;

    public int countTeams() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM teams", Integer.class);
    }

    public List<JdbcTeam> listTeams() {
        // query() gets RowMapper as a lambda to map the result set to the Team object
        return jdbcTemplate.query("SELECT * FROM teams", (rs, rowNum) -> {
            JdbcTeam jdbcTeam = new JdbcTeam();
            jdbcTeam.setId(rs.getInt("id"));
            jdbcTeam.setName(rs.getString("name"));
            return jdbcTeam;
        });
    }

    public JdbcTeam getTeam(int id) {
        // queryForObject() to get a single row
        // BeanPropertyRowMapper also maps the result set to the Team object
        return jdbcTemplate.queryForObject("SELECT * FROM teams WHERE id = ?", new BeanPropertyRowMapper<>(JdbcTeam.class), id);
    }
}
