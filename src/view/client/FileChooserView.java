package view.client;
import javax.swing.*;

import util.ApplicationResources;

import java.io.*;
/**
 * 上传文件时用于选择文件的框
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
		returnValue = chooser.showOpenDialog(this);  // 打开"打开文件"对话框
		if(returnValue == JFileChooser.APPROVE_OPTION)
			file = chooser.getSelectedFile();
	
	}
	/**
	 * 获得选择的文件父路径
	 * @return 文件父路径
	 */
	public String getSelectedFilePath(){
		String filePath = null;
		if(file != null)
			filePath = file.getAbsolutePath();
		
		return filePath;
	}
	/**
	 * 获得选择的文件名称
	 * @return 文件名称
	 */
	public String getSelectedFileName(){
		String fileName = null;
		if(file != null)
			fileName = file.getName();
		
		return fileName;		
	}
	
	
}


