package module.server;

import java.sql.SQLException;

import util.message.UserMsg;

public interface IUserDataBase {
	/**
	 * ����һ��user
	 * @param user
	 * @throws SQLException 
	 */
	public void addUser(UserMsg user) throws SQLException;
	/**
	 * ����ɾ��һ��User
	 * @param id
	 * @throws SQLException 
	 */
	public void deleteUser(String id) throws SQLException;
	/**
	 * �޸�User���ϣ�����id�ǲ��ܱ��޸ĵ�
	 * @param user
	 * @throws SQLException 
	 */
	public void updateUser(UserMsg user) throws SQLException;
	/**
	 * ����User��idȡ��user�������Ϣ
	 * @param id
	 * @throws SQLException 
	 */
	public UserMsg getUserByID(String id) throws SQLException;
	/**
	 * �������ݿ������е��û���Ϣ
	 * @return
	 * @throws SQLException
	 */
	public UserMsg[] getAllUser()throws SQLException;
}
