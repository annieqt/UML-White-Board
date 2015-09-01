package module.inter;

import java.rmi.RemoteException;

import whiteboard.umldraw.structure.StructureDiagram;

/**
 * Created by IntelliJ IDEA.
 * User: qrs
 * Date: 12-3-28
 * Time: 涓嬪崍9:44
 * To change this template use File | Settings | File Templates.
 */
public interface WhiteBoardDAO {
	
	/**
	 * 发送更新白板上的所有内容
	 * @throws RemoteException 
	 */
	public void sendWBUpdateInfo(StructureDiagram diagram) throws RemoteException;
	
	/**
	 * 获得白板更新
	 */
	public void getWBUpdateInfo(String senderId,StructureDiagram diagram);

	/**服务器主动要求此白板去更新别人。
	 * @throws RemoteException
	 */
	public void sendWBInitUpdate() throws RemoteException;
	
	public void requestWBInitUpdate() throws RemoteException;
}
