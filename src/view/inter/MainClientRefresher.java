package view.inter;

import util.message.UserMsg;
import whiteboard.umldraw.structure.StructureDiagram;

public interface MainClientRefresher {
	
	/**���������촰�������һ�仰
	 * @param text */
	public void addText(String text);
	
	/**ע�⣡�������ڻ���֪�����������Ժ��Լ��� */
	public void refreshFileList(String[] fileNames);
	
	/**���·����Ա�б����ʾ��
	 * @param members */
	public void refreshMemberList(UserMsg[] members);
	
	/**
	 * ���°װ�����
	 * @param diagram
	 */
	public void updateWBInfo(StructureDiagram diagram);
	
	
	public StructureDiagram getDiagram();
}
