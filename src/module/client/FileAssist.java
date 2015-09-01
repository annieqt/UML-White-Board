package module.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import util.ApplicationResources;
import util.command.RefreshListCommand;

import module.inter.ClientInfoProvider;
import module.inter.FileDAO;


public class FileAssist extends Assistant implements FileDAO{
	public FileAssist(ClientInfoProvider client) {
		super(client);
	}
	/**
	 * 上传完文件后自动刷新当前文件列表
	 */
	public void upload(String filePath, String fileName) throws RemoteException {
		if(filePath == null){
			//JOptionPane.showMessageDialog(null, "路径名为空，请重新选择下载路径");
			return;
		}
		if(fileName == null){
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("client.file.down.err.null"));
			return;
		}
		
		File file = new File(filePath);
		int fileSize = (int)file.length();
		if(fileSize > 1024*1024*10){
			throw new RemoteException("Error:<The File is too big!>");
		}
		byte content[] = new byte[fileSize];				
		
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
			input.read(content,0,content.length);
			input.close();
			getServer().handleUpload(content, fileName);
			System.out.println("文件上传完毕。");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RefreshListCommand c = new RefreshListCommand(getNowRoom().id);
		getServer().sendCommand(c);
		JOptionPane.showMessageDialog(null,ApplicationResources.getInstance().getString("client.file.up.succ"));
	}

	public void download(String putPath, String fileName) throws RemoteException {
		byte[] content = getServer().handleDownload(fileName); 
		if(putPath == null){
			//JOptionPane.showMessageDialog(null, "路径名为空，请重新选择文件后上传");
			return;
		}
		if(fileName == null){
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("client.file.up.err.null"));
			return;
		}
		BufferedOutputStream output;
		File file = new File(putPath);
		
		try {
			file.createNewFile();
			output = new BufferedOutputStream(new FileOutputStream(file));
			output.write(content, 0, content.length);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("client.file.down.succ"));
	}
	
	public void refreshFileList() throws RemoteException {		
		String[] fileNames = getServer().getFileNames();
		getClientView().refreshFileList(fileNames);		
	}
	
}
