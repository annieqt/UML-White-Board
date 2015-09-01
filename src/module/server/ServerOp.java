package module.server;

import java.sql.SQLException;

import util.message.UserMsg;

public interface ServerOp {
	public void close();

	/**按照给出的信息添加一个用户。
	 * @param usr */
	public boolean addUser(UserMsg usr);
	
	/**按照给出的信息，把对应id的用户信息以usr信息覆盖。
	 * @param usr */
	public boolean editUser(UserMsg usr);
	
	/**删掉对应id的用户。
	 * @param id */
	public boolean deleteUser(String id);

	public UserMsg[] getAllUsers() throws SQLException ;
}
