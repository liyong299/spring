package com.ly.test.spring.ftp�ϴ�;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * SFTP(Secure File Transfer Protocol)����ȫ�ļ�����Э�顣
 * @version 1.0 2014/12/18
 * @author dongliyang
 */
public class Sftp {

	/** ��־��¼��  */
	private Logger logger = Logger.getLogger(Sftp.class);
	/** Session */
	private Session session = null;
	/** Channel */
	private ChannelSftp channel = null;
	/** SFTP������IP��ַ */
	private String host;
	/** SFTP�������˿� */
	private int port;
	/** ���ӳ�ʱʱ�䣬��λ����  */
	private int timeout;

	/** �û��� */
	private String username;
	/** ���� */
	private String password;

	/**
	 * SFTP ��ȫ�ļ�����Э��
	 * @param host SFTP������IP��ַ
	 * @param port SFTP�������˿�
	 * @param timeout ���ӳ�ʱʱ�䣬��λ���� 
	 * @param username �û���
	 * @param password ����
	 */
	public Sftp(String host, int port, int timeout, String username, String password) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.username = username;
		this.password = password;
	}

	/**
	 * ��½SFTP������
	 * @return boolean
	 */
	public boolean login() {

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(username, host, port);
			if (password != null) {
				session.setPassword(password);
			}
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setTimeout(timeout);
			session.connect();
			logger.debug("sftp session connected");

			logger.debug("opening channel");
			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			logger.debug("connected successfully");
			return true;
		} catch (JSchException e) {
			logger.error("sftp login failed", e);
			return false;
		}
	}

	/**
	 * �ϴ��ļ�
	 * <p>
	 * ʹ��ʾ����SFTP�������ϵ�Ŀ¼�ṹ���£�/testA/testA_B/
	 * <table border="1">
	 * <tr><td>��ǰĿ¼</td><td>����</td><td>����������·��/���·��</td><td>�ϴ���</td></tr>
	 * <tr><td>/</td><td>uploadFile("testA","upload.txt",new FileInputStream(new File("up.txt")))</td><td>���·��</td><td>/testA/upload.txt</td></tr>
	 * <tr><td>/</td><td>uploadFile("testA/testA_B","upload.txt",new FileInputStream(new File("up.txt")))</td><td>���·��</td><td>/testA/testA_B/upload.txt</td></tr>
	 * <tr><td>/</td><td>uploadFile("/testA/testA_B","upload.txt",new FileInputStream(new File("up.txt")))</td><td>����·��</td><td>/testA/testA_B/upload.txt</td></tr>
	 * </table>
	 * </p>
	 * @param pathName SFTP������Ŀ¼
	 * @param fileName �������ϱ�����ļ���
	 * @param input �����ļ���
	 * @return boolean
	 */
	public boolean uploadFile(String pathName, String fileName, InputStream input) {

		String currentDir = currentDir();
		if (!changeDir(pathName)) {
			return false;
		}

		try {
			channel.put(input, fileName, ChannelSftp.OVERWRITE);
			if (!existFile(fileName)) {
				logger.debug("upload failed");
				return false;
			}
			logger.debug("upload successful");
			return true;
		} catch (SftpException e) {
			logger.error("upload failed", e);
			return false;
		} finally {
			changeDir(currentDir);
		}
	}

	/**
	 * �ϴ��ļ�
	 * <p>
	 * ʹ��ʾ����SFTP�������ϵ�Ŀ¼�ṹ���£�/testA/testA_B/
	 * <table border="1">
	 * <tr><td>��ǰĿ¼</td><td>����</td><td>����������·��/���·��</td><td>�ϴ���</td></tr>
	 * <tr><td>/</td><td>uploadFile("testA","upload.txt","up.txt")</td><td>���·��</td><td>/testA/upload.txt</td></tr>
	 * <tr><td>/</td><td>uploadFile("testA/testA_B","upload.txt","up.txt")</td><td>���·��</td><td>/testA/testA_B/upload.txt</td></tr>
	 * <tr><td>/</td><td>uploadFile("/testA/testA_B","upload.txt","up.txt")</td><td>����·��</td><td>/testA/testA_B/upload.txt</td></tr>
	 * </table>
	 * </p>
	 * @param pathName SFTP������Ŀ¼
	 * @param fileName �������ϱ�����ļ���
	 * @param localFile �����ļ�
	 * @return boolean
	 */
	public boolean uploadFile(String pathName, String fileName, String localFile) {

		String currentDir = currentDir();
		if (!changeDir(pathName)) {
			return false;
		}

		try {
			channel.put(localFile, fileName, ChannelSftp.OVERWRITE);
			if (!existFile(fileName)) {
				logger.debug("upload failed");
				return false;
			}
			logger.debug("upload successful");
			return true;
		} catch (SftpException e) {
			logger.error("upload failed", e);
			return false;
		} finally {
			changeDir(currentDir);
		}
	}

	/**
	 * �����ļ�
	 * <p>
	 * ʹ��ʾ����SFTP�������ϵ�Ŀ¼�ṹ���£�/testA/testA_B/
	 * <table border="1">
	 * <tr><td>��ǰĿ¼</td><td>����</td><td>����������·��/���·��</td><td>���غ�</td></tr>
	 * <tr><td>/</td><td>downloadFile("testA","down.txt","D:\\downDir")</td><td>���·��</td><td>D:\\downDir\\down.txt</td></tr>
	 * <tr><td>/</td><td>downloadFile("testA/testA_B","down.txt","D:\\downDir")</td><td>���·��</td><td>D:\\downDir\\down.txt</td></tr>
	 * <tr><td>/</td><td>downloadFile("/testA/testA_B","down.txt","D:\\downDir")</td><td>����·��</td><td>D:\\downDir\\down.txt</td></tr>
	 * </table>
	 * </p>
	 * @param remotePath SFTP������Ŀ¼
	 * @param fileName ����������Ҫ���ص��ļ���
	 * @param localPath ���ر���·��
	 * @return boolean
	 */
	public boolean downloadFile(String remotePath, String fileName, String localPath) {

		String currentDir = currentDir();
		if (!changeDir(remotePath)) {
			return false;
		}

		try {
			String localFilePath = localPath + File.separator + fileName;
			channel.get(fileName, localFilePath);

			File localFile = new File(localFilePath);
			if (!localFile.exists()) {
				logger.debug("download file failed");
				return false;
			}
			logger.debug("download successful");
			return true;
		} catch (SftpException e) {
			logger.error("download file failed", e);
			return false;
		} finally {
			changeDir(currentDir);
		}
	}

	/**
	 * �л�����Ŀ¼
	 * <p>
	 * ʹ��ʾ����SFTP�������ϵ�Ŀ¼�ṹ���£�/testA/testA_B/
	 * <table border="1">
	 * <tr><td>��ǰĿ¼</td><td>����</td><td>����(����·��/���·��)</td><td>�л����Ŀ¼</td></tr>
	 * <tr><td>/</td><td>changeDir("testA")</td><td>���·��</td><td>/testA/</td></tr>
	 * <tr><td>/</td><td>changeDir("testA/testA_B")</td><td>���·��</td><td>/testA/testA_B/</td></tr>
	 * <tr><td>/</td><td>changeDir("/testA")</td><td>����·��</td><td>/testA/</td></tr>
	 * <tr><td>/testA/testA_B/</td><td>changeDir("/testA")</td><td>����·��</td><td>/testA/</td></tr>
	 * </table>
	 * </p>
	 * @param pathName ·��
	 * @return boolean
	 */
	public boolean changeDir(String pathName) {
		if (pathName == null || pathName.trim().equals("")) {
			logger.debug("invalid pathName");
			return false;
		}

		try {
			channel.cd(pathName.replaceAll("\\\\", "/"));
			logger.debug("directory successfully changed,current dir=" + channel.pwd());
			return true;
		} catch (SftpException e) {
			logger.error("failed to change directory", e);
			return false;
		}
	}

	/**
	 * �л�����һ��Ŀ¼
	 * <p>
	 * ʹ��ʾ����SFTP�������ϵ�Ŀ¼�ṹ���£�/testA/testA_B/
	 * <table border="1">
	 * <tr><td>��ǰĿ¼</td><td>����</td><td>�л����Ŀ¼</td></tr>
	 * <tr><td>/testA/</td><td>changeToParentDir()</td><td>/</td></tr>
	 * <tr><td>/testA/testA_B/</td><td>changeToParentDir()</td><td>/testA/</td></tr>
	 * </table>
	 * </p>
	 * @return boolean
	 */
	public boolean changeToParentDir() {
		return changeDir("..");
	}

	/**
	 * �л�����Ŀ¼
	 * @return boolean
	 */
	public boolean changeToHomeDir() {
		String homeDir = null;
		try {
			homeDir = channel.getHome();
		} catch (SftpException e) {
			logger.error("can not get home directory", e);
			return false;
		}
		return changeDir(homeDir);
	}

	/**
	 * ����Ŀ¼
	 * <p>
	 * ʹ��ʾ����SFTP�������ϵ�Ŀ¼�ṹ���£�/testA/testA_B/
	 * <table border="1">
	 * <tr><td>��ǰĿ¼</td><td>����</td><td>����(����·��/���·��)</td><td>�����ɹ����Ŀ¼</td></tr>
	 * <tr><td>/testA/testA_B/</td><td>makeDir("testA_B_C")</td><td>���·��</td><td>/testA/testA_B/testA_B_C/</td></tr>
	 * <tr><td>/</td><td>makeDir("/testA/testA_B/testA_B_D")</td><td>����·��</td><td>/testA/testA_B/testA_B_D/</td></tr>
	 * </table>
	 * <br/>
	 * <b>ע��</b>����<b>�м�Ŀ¼������</b>������£����ܹ�ʹ�þ���·���ķ�ʽ���������м�Ŀ¼��Ŀ��Ŀ¼��
	 * ����makeDir("/testNOEXIST1/testNOEXIST2/testNOEXIST3")�����Ǵ���ġ�
	 * </p>
	 * @param dirName Ŀ¼
	 * @return boolean
	 */
	public boolean makeDir(String dirName) {
		try {
			channel.mkdir(dirName);
			logger.debug("directory successfully created,dir=" + dirName);
			return true;
		} catch (SftpException e) {
			logger.error("failed to create directory", e);
			return false;
		}
	}

	/**
	 * ɾ���ļ���
	 * @param dirName
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean delDir(String dirName) {
		if (!changeDir(dirName)) {
			return false;
		}

		Vector<LsEntry> list = null;
		try {
			list = channel.ls(channel.pwd());
		} catch (SftpException e) {
			logger.error("can not list directory", e);
			return false;
		}

		for (LsEntry entry : list) {
			String fileName = entry.getFilename();
			if (!fileName.equals(".") && !fileName.equals("..")) {
				if (entry.getAttrs().isDir()) {
					delDir(fileName);
				} else {
					delFile(fileName);
				}
			}
		}

		if (!changeToParentDir()) {
			return false;
		}

		try {
			channel.rmdir(dirName);
			logger.debug("directory " + dirName + " successfully deleted");
			return true;
		} catch (SftpException e) {
			logger.error("failed to delete directory " + dirName, e);
			return false;
		}
	}

	/**
	 * ɾ���ļ�
	 * @param fileName �ļ���
	 * @return boolean
	 */
	public boolean delFile(String fileName) {
		if (fileName == null || fileName.trim().equals("")) {
			logger.debug("invalid filename");
			return false;
		}

		try {
			channel.rm(fileName);
			logger.debug("file " + fileName + " successfully deleted");
			return true;
		} catch (SftpException e) {
			logger.error("failed to delete file " + fileName, e);
			return false;
		}
	}

	/**
	 * ��ǰĿ¼���ļ����ļ��������б�
	 * @return String[]
	 */
	public String[] ls() {
		return list(Filter.ALL);
	}

	/**
	 * ָ��Ŀ¼���ļ����ļ��������б�
	 * @return String[]
	 */
	public String[] ls(String pathName) {
		String currentDir = currentDir();
		if (!changeDir(pathName)) {
			return new String[0];
		}
		;
		String[] result = list(Filter.ALL);
		if (!changeDir(currentDir)) {
			return new String[0];
		}
		return result;
	}

	/**
	 * ��ǰĿ¼���ļ������б�
	 * @return String[]
	 */
	public String[] lsFiles() {
		return list(Filter.FILE);
	}

	/**
	 * ָ��Ŀ¼���ļ������б�
	 * @return String[]
	 */
	public String[] lsFiles(String pathName) {
		String currentDir = currentDir();
		if (!changeDir(pathName)) {
			return new String[0];
		}
		;
		String[] result = list(Filter.FILE);
		if (!changeDir(currentDir)) {
			return new String[0];
		}
		return result;
	}

	/**
	 * ��ǰĿ¼���ļ��������б�
	 * @return String[]
	 */
	public String[] lsDirs() {
		return list(Filter.DIR);
	}

	/**
	 * ָ��Ŀ¼���ļ��������б�
	 * @return String[]
	 */
	public String[] lsDirs(String pathName) {
		String currentDir = currentDir();
		if (!changeDir(pathName)) {
			return new String[0];
		}
		;
		String[] result = list(Filter.DIR);
		if (!changeDir(currentDir)) {
			return new String[0];
		}
		return result;
	}

	/**
	 * ��ǰĿ¼�Ƿ�����ļ����ļ���
	 * @param name ����
	 * @return boolean
	 */
	public boolean exist(String name) {
		return exist(ls(), name);
	}

	/**
	 * ָ��Ŀ¼�£��Ƿ�����ļ����ļ���
	 * @param path Ŀ¼
	 * @param name ����
	 * @return boolean
	 */
	public boolean exist(String path, String name) {
		return exist(ls(path), name);
	}

	/**
	 * ��ǰĿ¼�Ƿ�����ļ�
	 * @param name �ļ���
	 * @return boolean
	 */
	public boolean existFile(String name) {
		return exist(lsFiles(), name);
	}

	/**
	 * ָ��Ŀ¼�£��Ƿ�����ļ�
	 * @param path Ŀ¼
	 * @param name �ļ���
	 * @return boolean
	 */
	public boolean existFile(String path, String name) {
		return exist(lsFiles(path), name);
	}

	/**
	 * ��ǰĿ¼�Ƿ�����ļ���
	 * @param name �ļ�������
	 * @return boolean
	 */
	public boolean existDir(String name) {
		return exist(lsDirs(), name);
	}

	/**
	 * ָ��Ŀ¼�£��Ƿ�����ļ���
	 * @param path Ŀ¼
	 * @param name �ļҼ�����
	 * @return boolean
	 */
	public boolean existDir(String path, String name) {
		return exist(lsDirs(path), name);
	}

	/**
	 * ��ǰ����Ŀ¼
	 * @return String
	 */
	public String currentDir() {
		try {
			return channel.pwd();
		} catch (SftpException e) {
			logger.error("failed to get current dir", e);
			return homeDir();
		}
	}

	/**
	 * �ǳ�
	 */
	public void logout() {
		if (channel != null) {
			channel.quit();
			channel.disconnect();
		}
		if (session != null) {
			session.disconnect();
		}
		logger.debug("logout successfully");
	}

	//------private method ------

	/** ö�٣����ڹ����ļ����ļ���  */
	private enum Filter {
		/** �ļ����ļ��� */
		ALL, /** �ļ� */
		FILE, /** �ļ��� */
		DIR
	};

	/**
	 * �г���ǰĿ¼�µ��ļ����ļ���
	 * @param filter ���˲���
	 * @return String[] 
	 */
	@SuppressWarnings("unchecked")
	private String[] list(Filter filter) {
		Vector<LsEntry> list = null;
		try {
			//ls�����᷵�����������Ŀ¼����ǰĿ¼(.)�͸�Ŀ¼(..)
			list = channel.ls(channel.pwd());
		} catch (SftpException e) {
			logger.error("can not list directory", e);
			return new String[0];
		}

		List<String> resultList = new ArrayList<String>();
		for (LsEntry entry : list) {
			if (filter(entry, filter)) {
				resultList.add(entry.getFilename());
			}
		}
		return resultList.toArray(new String[0]);
	}

	/**
	 * �ж��Ƿ��Ƿ��������
	 * @param entry LsEntry
	 * @param f ���˲���
	 * @return boolean
	 */
	private boolean filter(LsEntry entry, Filter f) {
		if (f.equals(Filter.ALL)) {
			return !entry.getFilename().equals(".") && !entry.getFilename().equals("..");
		} else if (f.equals(Filter.FILE)) {
			return !entry.getFilename().equals(".") && !entry.getFilename().equals("..") && !entry.getAttrs().isDir();
		} else if (f.equals(Filter.DIR)) {
			return !entry.getFilename().equals(".") && !entry.getFilename().equals("..") && entry.getAttrs().isDir();
		}
		return false;
	}

	/**
	 * ��Ŀ¼
	 * @return String
	 */
	private String homeDir() {
		try {
			return channel.getHome();
		} catch (SftpException e) {
			return "/";
		}
	}

	/**
	 * �ж��ַ����Ƿ������������
	 * @param strArr �ַ�������
	 * @param str �ַ���
	 * @return boolean
	 */
	private boolean exist(String[] strArr, String str) {
		if (strArr == null || strArr.length == 0) {
			return false;
		}
		if (str == null || str.trim().equals("")) {
			return false;
		}
		for (String s : strArr) {
			if (s.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

	//------private method ------
}