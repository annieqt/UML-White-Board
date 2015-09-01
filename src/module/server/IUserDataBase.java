package module.server;

import java.sql.SQLException;

import util.message.UserMsg;

public interface IUserDataBase {
	/**
	 * 增加一个user
	 * @param user
	 * @throws SQLException 
	 */
	public void addUser(UserMsg user) throws SQLException;
	/**
	 * 根据删除一个User
	 * @param id
	 * @throws SQLException 
	 */
	public void deleteUser(String id) throws SQLException;
	/**
	 * 修改User资料，其中id是不能被修改的
	 * @param user
	 * @throws SQLException 
	 */
	public void updateUser(UserMsg user) throws SQLException;
	/**
	 * 根据User的id取的user的相关信息
	 * @param id
	 * @throws SQLException 
	 */
	public UserMsg getUserByID(String id) throws SQLException;
	/**
	 * 返回数据库中所有的用户信息
	 * @return
	 * @throws SQLException
	 */
	public UserMsg[] getAllUser()throws SQLException;
}
