package module.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import control.inter.ClientReceiver;

import util.command.Command;
import util.message.RoomMsg;
import util.message.RoomState;
import util.message.UserMsg;


/**�������˹��ͻ���Զ�̵��õĽӿڡ�
 * @author Playeye */
public interface RemoteMeetingServer extends Remote {

	/**�û���¼ϵͳ������������Ϣ��
	 * @param sname �û���
	 * @param password ����
	 * @return �û�����Ϣ
	 * @param clientReceiver �ͻ��˵�Զ������
	 * @throws RemoteException */
	public UserMsg sign(String sname,String password) throws RemoteException, ServerNotActiveException;
	
	/**�û�����һ������
	 * @param user �û���Ϣ
	 * @param roomId �����
	 * @return ����ķ�����Ϣ */
	public RoomMsg joinRoom(UserMsg user, int roomId ,ClientReceiver clientReceiver)throws RemoteException;

	/**�û��½�һ������
	 * @param user �û���Ϣ
	 * @param name ������
	 * @param maxMem �������
	 * @return �����ķ�����Ϣ
	 */
	public RoomMsg createRoom(UserMsg user, String name, int maxMem,ClientReceiver clientReceiver)throws RemoteException;
	
	/**��ĳ�û��˳����䣬���ش���	 
	 * @param user �û���Ϣ
	 * @param room ������Ϣ*/
	public boolean exitRoom(UserMsg user)throws RemoteException;
	

	/**��һ����������䵽������ȥ��
	 * @param c */
	public void sendCommand(Command c) throws RemoteException;
	/**
	 * ����ͻ��˵��ļ����ز���
	 * @param fileName �ļ�����
	 * @return �ļ����ݣ�byte[]
	 * @throws RemoteException 
	 */
	public byte[] handleDownload(String fileName) throws RemoteException;
	/**
	 * ����ͻ��˵��ļ��ϴ�����
	 * @param content �ļ�����
	 * @param fileName �ļ�����
	 */
	public void handleUpload(byte[] content, String fileName)throws RemoteException;
	/**
	 * ȡ�÷��������е��ļ������б�
	 * @return �ļ������б�
	 */
	public String[] getFileNames()throws RemoteException;
	
	public RoomState[] getRooms()throws RemoteException;
	
	public UserMsg[] getRoomUsers(int roomId)throws RemoteException;
	
	public void requestRefreshWB(UserMsg user)throws RemoteException;
	
}
