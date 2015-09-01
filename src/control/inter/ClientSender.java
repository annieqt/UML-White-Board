package control.inter;

import java.rmi.RemoteException;

import whiteboard.umldraw.structure.StructureDiagram;


/**�ͻ������ڷ�����Ϣ�Ľӿڡ��˽ӿڸ�����������ӿڽ������ӡ�
 * @author Playeye */
public interface ClientSender {
	
	/**�ϴ�һ���ļ�����������
	 * @param filePath �ϴ����ļ����ϴ��߻����ĵ�ַ
	 * @param filename �ļ��� 
	 * @throws RemoteException */
	public void upload(String filePath,String filename) throws RemoteException;
	
	/**����һ���ļ���������
	 * @param putPath Ҫ���ص��ļ������ڱ����ĵ�ַ
	 * @param filename �ļ���
	 * @throws RemoteException */
	public void download(String putPath,String filename) throws RemoteException;
		
	/**����һ��������Ϣ��
	 * @param text ��Ϣ�����ݡ�	 
	 * @throws RemoteException */
	public void sendText(String text) throws RemoteException;
	
	public void refreshAll() throws RemoteException;
	
	public void disconnect() throws RemoteException;

	
	/**������������
	 * @param v	 */
	public void setSoundVolume(int v);
	
	/**�������쾲��
	 * @param v	 */
	public void setSoundMute(boolean mute,int volumn);
	
	/**
	 * ���Ͱװ�ĸ�����Ϣ
	 */
	public void sendWBUpdateInfo(StructureDiagram diagram) throws RemoteException;
	
}
