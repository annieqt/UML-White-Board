package module.inter;

import java.rmi.RemoteException;

public interface FileDAO {
	
	/**上传一个文件到服务器。
	 * @param filePath 上传的文件在上传者机器的地址
	 * @param putPath 在服务器上放置文件的地址 
	 * @throws RemoteException */
	public void upload(String filePath,String putPath) throws RemoteException;
	
	/**下载一个文件到本机。
	 * @param fromPath 要下载的在服务器上的放置地址
	 * @param putPath 下载到下载者机器的路径
	 * @throws RemoteException */
	public void download(String fromPath,String putPath) throws RemoteException;
	
	
	/**将底层的文件列表刷新后更新到界面里。
	 * @throws RemoteException */
	public void refreshFileList() throws RemoteException;
}
