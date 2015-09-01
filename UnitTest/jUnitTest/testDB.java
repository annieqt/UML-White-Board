package jUnitTest;

import static org.junit.Assert.*;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import util.message.UserMsg;

import module.server.UserDatabase;

/**
 * 对数据库访问进行测试
 * 确保之前已经正确安装数据库
 * @author wt
 *
 */
public class testDB {
	UserDatabase handler = null;
	
	@Before
	public void before() throws SQLException{
		handler = new UserDatabase("root","tiancai");
	}
	
	@Test
	public void testGet() throws SQLException{
		UserMsg user = handler.getUserByID("u3");
		assertEquals("u3", user.id);
	}
	
	@Test
	public void testDelete() throws SQLException{
		UserMsg user = null;
		handler.deleteUser("u3");
		user = handler.getUserByID("u3");
		assertEquals(user, null);
	}
	@Test
	public void testAdd() throws SQLException{
		UserMsg user = new UserMsg("u3", "bob", "tiancai");
		handler.addUser(user);
		user = handler.getUserByID("u3");
		assertEquals(user.id, "u3");
		
	}
	@Test
	public void testGetAll() throws SQLException{
		UserMsg users[] = handler.getAllUser();
		assertEquals(2, users.length);
	}
}
