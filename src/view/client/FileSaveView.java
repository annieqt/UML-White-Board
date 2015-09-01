package view.client;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import util.ApplicationResources;
/**
 * 文件下载时需要的选择存放文件路径的对话框
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
		returnValue = chooser.showOpenDialog(this);  // 打开"打开文件"对话框
		if(returnValue == JFileChooser.APPROVE_OPTION)
			file = chooser.getSelectedFile();
	}
	/**
	 * 获得当前目录路径
	 * @return 当前目录路径
	 */
	public String getPresentPath(){
		String filePath = null;
		if(file != null)
			filePath = file.getAbsolutePath();
		
		return filePath;
	}
	/**
	 * 获得用户设定的文件名称
	 * @return 文件名称
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
