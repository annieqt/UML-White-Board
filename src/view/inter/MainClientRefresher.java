package view.inter;

import util.message.UserMsg;
import whiteboard.umldraw.structure.StructureDiagram;

public interface MainClientRefresher {
	
	/**在文字聊天窗口里添加一句话
	 * @param text */
	public void addText(String text);
	
	/**注意！参数现在还不知道，所以你以后自己改 */
	public void refreshFileList(String[] fileNames);
	
	/**更新房间成员列表的显示。
	 * @param members */
	public void refreshMemberList(UserMsg[] members);
	
	/**
	 * 更新白板内容
	 * @param diagram
	 */
	public void updateWBInfo(StructureDiagram diagram);
	
	
	public StructureDiagram getDiagram();
}
