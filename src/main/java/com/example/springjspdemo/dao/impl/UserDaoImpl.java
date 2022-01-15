package com.example.springjspdemo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.springjspdemo.constants.Constants;
import com.example.springjspdemo.controller.bean.User;
import com.example.springjspdemo.dao.UserDao;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public User getUserById(String userId) {
		String sql = "select cast(aes_decrypt(unhex(`user_pswd`), 'secret') as char(50)), role_id\r\n"
				+ "from user \r\n" + "join user_role on user.id = user_role.user_id\r\n" + "where user.user_id=?";

		return getJdbcTemplate().queryForObject(sql, new Object[] { userId }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(userId);
				user.setPassword(rs.getString(1));
				user.setRoleId(rs.getInt(2));
				return user;
			}

		});
	}

	@Override
	public String createNewUser(User user) {
		
		if (getUserById(user.getUserId()) != null) {
			return Constants.REGISTER_FAILED_DUPLICATE_USERID;
		}
		
		final String sql1 = "insert into user(user_id, user_pswd) values(?, hex(aes_encrypt(?, 'secret')));";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql1, new String[] { "id" });
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getPassword());

				return ps;
			}
		}, keyHolder);

		Long id = keyHolder.getKey().longValue();
		final String sql2 = "insert into user_role(role_id, user_id) values(?, ?)";

		int count = getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql2);
				ps.setInt(1, Constants.ROLE_EMPLOYEE);
				ps.setLong(2, id);

				return ps;
			}
		});
		
		if (count == 1) {
			return Constants.REGISTER_SUCCESS;
		}
		
		return Constants.REGISTER_FAILED;
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "select * from user";

		return getJdbcTemplate().query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong(1));
				user.setUserId(rs.getString(2));
				return user;
			}

		});
	}

}
