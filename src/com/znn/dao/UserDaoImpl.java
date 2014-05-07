package com.znn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.znn.vo.User;

public class UserDaoImpl implements UserDao {
	 private JdbcTemplate jdbcTemplate;
	 public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
//	        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    }
	@Override
	public List<User> query(){
		List<User > list = this.jdbcTemplate.query("select * from spring_user",  new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				User user = new User();
				user.setName(resultSet.getString("name"));
				user.setId(resultSet.getInt("id"));
				user.setAge(resultSet.getInt("age"));
				return user;
			}} );
		return list;
	}
	@Override
	public int save(User user) {
		return this.jdbcTemplate.update("insert into spring_user (name,age) values(?,?)", user.getName(),user.getAge());
	}
	@Override
	public int update(User user) {
		return this.jdbcTemplate.update("update spring_user set name=?,age=? where id=?", user.getName(),user.getAge(),user.getId());
	}
	@Override
	public int delete(int id) {
		return this.jdbcTemplate.update("delete from spring_user where id=?",id);
	}
	@Override
	public User queryAUser(int id) {
		//若查不到或查到多条都会报异常
//		User user1 = this.jdbcTemplate.queryForObject("select * from spring_user where id=?",new Object[]{id}, new RowMapper<User>(){
//
//			@Override
//			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//				User user = new User();
//				user.setName(resultSet.getString("name"));
//				user.setId(resultSet.getInt("id"));
//				user.setAge(resultSet.getInt("age"));
//				return user;
//			}});
		List<User> users = this.jdbcTemplate.query("select * from spring_user where id=?",new Object[]{id}, new RowMapper<User>(){
			
			@Override
			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				User user = new User();
				user.setName(resultSet.getString("name"));
				user.setId(resultSet.getInt("id"));
				user.setAge(resultSet.getInt("age"));
				return user;
			}});
		if (users.size()==0) {
			return null;
		}else {
			return users.get(0);
		}
	}
}
