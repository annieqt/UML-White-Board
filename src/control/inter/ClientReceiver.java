package control.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import util.message.UserMsg;
import whiteboard.umldraw.structure.StructureDiagram;




/**�������˻ص��ͻ������õĽӿڡ��ͻ��˱���Ҫʵ������ӿڡ�
 * @author Playeye */

public interface ClientReceiver extends Remote{
	
	/**ˢ���ļ��б�	 */
	public void refreshFileList() throws RemoteException;
	
	/**ˢ�·����Ա�б�	 */
	public void refreshMemberList() throws RemoteException;
	
	/**��������Ϣ���������һ����Ϣ��	 */
	public void addText(UserMsg sender,String message) throws RemoteException;
	
	/**һ���˸ս��������������������ӡ�
	 * @param ip �����˵�ip*/
	public void audioConnect(ArrayList<String> ip)throws RemoteException;
	
	/**��Һ�һ���ս����������ӡ�
	 * @param ip */
	public void audioConnect(String ip,int No)throws RemoteException;
	
	/**
	 * ���°װ�����
	 * @throws RemoteException
	 */
	public void getWBUpdateInfo(String senderId,StructureDiagram diagram) throws RemoteException;
	
	/** ������Ҫ��˰װ��������ͳ�ʼ������
	 */
	public void sendWBInitUpdate() throws RemoteException;
}
