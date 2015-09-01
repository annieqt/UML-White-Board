package view.client;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import util.ApplicationResources;
/**
 * �ļ�����ʱ��Ҫ��ѡ�����ļ�·���ĶԻ���
 * @author wt
 *
 */
public class FileSaveView extends JFrame{
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PATH = "C:\\";
	private JFileChooser chooser;
	private File file = null;
	private int returnValue = -1;
	
	public FileSaveView (){
		chooser = new JFileChooser(DEFAULT_PATH);
		chooser.setApproveButtonText(ApplicationResources.getInstance().getString("client.file.save"));
		chooser.setDialogTitle(ApplicationResources.getInstance().getString("client.file.save.title"));
		returnValue = chooser.showOpenDialog(this);  // ��"���ļ�"�Ի���
		if(returnValue == JFileChooser.APPROVE_OPTION)
			file = chooser.getSelectedFile();
	}
	/**
	 * ��õ�ǰĿ¼·��
	 * @return ��ǰĿ¼·��
	 */
	public String getPresentPath(){
		String filePath = null;
		if(file != null)
			filePath = file.getAbsolutePath();
		
		return filePath;
	}
	/**
	 * ����û��趨���ļ�����
	 * @return �ļ�����
	 */
	public String getFileName(){
		String fileName = null;
		if(file != null)
			fileName = file.getName();
		
		return fileName;		
	}
	/*public static void main(String a[]){
		FileSaveView aa = new FileSaveView();
		System.out.println(aa.getFileName());
		System.out.println(aa.getPresentPath());
	}*/
}
