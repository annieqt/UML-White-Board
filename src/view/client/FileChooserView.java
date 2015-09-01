package view.client;
import javax.swing.*;

import util.ApplicationResources;

import java.io.*;
/**
 * �ϴ��ļ�ʱ����ѡ���ļ��Ŀ�
 * @author annie
 *
 */
public class FileChooserView  extends JFrame{
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PATH = "C:\\";
	private JFileChooser chooser;
	private File file = null;
	private int returnValue = -1;
	
	public FileChooserView (){
		chooser = new JFileChooser(DEFAULT_PATH);	
		chooser.setApproveButtonText(ApplicationResources.getInstance().getString("client.file.up"));
		chooser.setDialogTitle(ApplicationResources.getInstance().getString("client.file.up.title"));
		returnValue = chooser.showOpenDialog(this);  // ��"���ļ�"�Ի���
		if(returnValue == JFileChooser.APPROVE_OPTION)
			file = chooser.getSelectedFile();
	
	}
	/**
	 * ���ѡ����ļ���·��
	 * @return �ļ���·��
	 */
	public String getSelectedFilePath(){
		String filePath = null;
		if(file != null)
			filePath = file.getAbsolutePath();
		
		return filePath;
	}
	/**
	 * ���ѡ����ļ�����
	 * @return �ļ�����
	 */
	public String getSelectedFileName(){
		String fileName = null;
		if(file != null)
			fileName = file.getName();
		
		return fileName;		
	}
	
	
}


