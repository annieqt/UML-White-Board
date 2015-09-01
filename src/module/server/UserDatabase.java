package module.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import module.server.DBConstants.UserTable;

import util.message.UserMsg;

public class UserDatabase implements IUserDataBase{
	private Connection conn ;
	private Statement stat = null;
	
	/**
	 * 构造函数初始化statement
	 * @throws SQLException 
	 */
	public UserDatabase(String sname,String password) throws SQLException{
		DBConnect.init(sname, password);
		conn = DBConnect.getConnection();
		if (conn != null) {
			stat = conn.createStatement();
		}		
	}
	@Override
	public void addUser(UserMsg user) throws SQLException {
		String sql = "insert into " + UserTable.TABLE_NAME+ "\n(" +
		UserTable.ID + ", "+
		UserTable.NAME + ", "+
		UserTable.PASSWORD +") values( \"" + user.id +"\", \"" + user.name +"\", \"" + user.password+"\" );";
		
		System.out.println(sql);
		stat.execute(sql);
	}
	
	@Override
	public void deleteUser(String id) throws SQLException {
		String sql = "delete from "+ UserTable.TABLE_NAME + "\n where "+
			UserTable.ID + " = \"" +id+"\";";
			
		System.out.println(sql);
		stat.execute(sql);
	}

	@Override
	public void updateUser(UserMsg user) throws SQLException {
		String sql  = "update " + UserTable.TABLE_NAME + " set " +
		UserTable.NAME + " = \""+ user.name +"\",\n"+
		UserTable.PASSWORD + " = \""+ user.password+"\" where " + 
		UserTable.ID+" = \"" +user.id + "\" ;";
		System.out.println(sql);
		stat.execute(sql);
	}

	@Override
	public UserMsg getUserByID(String id) throws SQLException {
		UserMsg user = null;
		String sql = "select * from " + UserTable.TABLE_NAME +" where "+ UserTable.ID +" = \"" + id + "\";";
		ResultSet rSet = stat.executeQuery(sql);
		if(rSet.next()){
			String name = rSet.getString(UserTable.NAME);
			String password = rSet.getString(UserTable.PASSWORD);
			user = new UserMsg(id, name, password);
		}		
		
		return user;
	}
	@Override
	public UserMsg[] getAllUser() throws SQLException {
		ArrayList<UserMsg> list = new ArrayList<UserMsg>();
		UserMsg user = null;
		String id,name,password;
		ResultSet rSet = stat.executeQuery(
				"select* from " + UserTable.TABLE_NAME+";"
				);
		while (rSet.next()) {
			id = rSet.getString(UserTable.ID);
			name = rSet.getString(UserTable.NAME);
			password  = rSet.getString(UserTable.PASSWORD);
			user = new UserMsg(id, name, password);
			list.add(user);
		}
		int length = list.size();
		UserMsg[] users = new UserMsg[length];
		for(int i = 0; i < length; i++)
			users[i] = list.get(i);
		return users;
	}
}
