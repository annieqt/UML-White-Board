package control.inter;

import java.rmi.RemoteException;

/**�û��ڷ���ѡ�������Ĳ����ӿڡ��ṩ�û����뷿��ķ�����
 * @author Playeye */
public interface RoomChooser {
	/**��¼�ڴ˵��û������Ӧid�ķ��䡣
	 * @param id
	 * @return �����Ƿ�ɹ� */
	public boolean joinRoom(int id);
	
	/**��¼���û�����һ���µķ���
	 * @param name ��������
	 * @param maxMem �������
	 * @return �����Ƿ�ɹ�	 */
	public boolean createRoom(String name,int maxMem);

	/**ˢ�·����б�	 
	 * @throws RemoteException */
	public void refreshRoomList() throws RemoteException;
}
