package module.inter;

import java.util.ArrayList;

public interface SoundDAO {

	public void audioConnect(ArrayList<String> ip);
	
	public void audioConnect(String ip,int No);

	public void audioDisconnect();
	
	
	/**������������
	 * @param v	 */
	public void setSoundVolume(int v);
	
	/**�������쾲��
	 * @param v	 */
	public void setSoundMute(boolean mute,int volumn);

}
