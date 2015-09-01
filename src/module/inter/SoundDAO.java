package module.inter;

import java.util.ArrayList;

public interface SoundDAO {

	public void audioConnect(ArrayList<String> ip);
	
	public void audioConnect(String ip,int No);

	public void audioDisconnect();
	
	
	/**调节音响音量
	 * @param v	 */
	public void setSoundVolume(int v);
	
	/**调节音响静音
	 * @param v	 */
	public void setSoundMute(boolean mute,int volumn);

}
