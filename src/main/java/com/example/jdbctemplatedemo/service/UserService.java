package com.example.jdbctemplatedemo.service;

import com.example.jdbctemplatedemo.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class UserService {

    public static final String INSERT_INTO_USER_USERNAME_ADDRESS_VALUES = "insert into user(username, address) values(?, ?);";
    public static final String SELECT_FROM_USER = "select * from user";
    private JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addUser1(User user) {
        return jdbcTemplate.update(INSERT_INTO_USER_USERNAME_ADDRESS_VALUES,
                user.getUsername(), user.getAddress());
    }

    public int addUser2(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_INTO_USER_USERNAME_ADDRESS_VALUES,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2,user.getAddress());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        System.out.println(user);
        return update;
    }

    public int deleteUserById(Long id) {
        return jdbcTemplate.update("delete from user where id = ?", id);
    }

    public int updateUser(User user) {
        return jdbcTemplate.update("update user set username = ?, address = ? where id = ?",
                user.getUsername(), user.getAddress(), user.getId());
    }

    public List<User> getAllUsers1() {
        return jdbcTemplate.query(SELECT_FROM_USER, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                String username = rs.getString("username");
                String address = rs.getString("address");
                Long id =rs.getLong("id");
                User user = new User(username, address);
                user.setId(id);
                return user;
            }
        });
    }

    public List<User> getAllUsers2() {
        return jdbcTemplate.query(SELECT_FROM_USER, new BeanPropertyRowMapper<>(User.class));
    }
}
