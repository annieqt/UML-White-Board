package module.inter;

import java.rmi.RemoteException;

public interface FileDAO {
	
	/**�ϴ�һ���ļ�����������
	 * @param filePath �ϴ����ļ����ϴ��߻����ĵ�ַ
	 * @param putPath �ڷ������Ϸ����ļ��ĵ�ַ 
	 * @throws RemoteException */
	public void upload(String filePath,String putPath) throws RemoteException;
	
	/**����һ���ļ���������
	 * @param fromPath Ҫ���ص��ڷ������ϵķ��õ�ַ
	 * @param putPath ���ص������߻�����·��
	 * @throws RemoteException */
	public void download(String fromPath,String putPath) throws RemoteException;
	
	
	/**���ײ���ļ��б�ˢ�º���µ������
	 * @throws RemoteException */
	public void refreshFileList() throws RemoteException;
}
