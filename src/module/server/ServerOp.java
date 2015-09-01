package module.server;

import java.sql.SQLException;

import util.message.UserMsg;

public interface ServerOp {
	public void close();

	/**���ո�������Ϣ���һ���û���
	 * @param usr */
	public boolean addUser(UserMsg usr);
	
	/**���ո�������Ϣ���Ѷ�Ӧid���û���Ϣ��usr��Ϣ���ǡ�
	 * @param usr */
	public boolean editUser(UserMsg usr);
	
	/**ɾ����Ӧid���û���
	 * @param id */
	public boolean deleteUser(String id);

	public UserMsg[] getAllUsers() throws SQLException ;
}
