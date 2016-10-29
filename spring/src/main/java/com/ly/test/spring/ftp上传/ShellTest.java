package com.ly.test.spring.ftp�ϴ�;

import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ShellTest {
	public static void main(String[] args) throws Exception {
		String command = " ls > aat.log \n";
		sshShell("172.16.34.48", "cec", "cec", 2234, command);
	}

	/**
	 * ����JSch��ʵ��Զ������SHELL����ִ��
	 * @param ip ����IP
	 * @param user ������½�û���
	 * @param psw  ������½����
	 * @param port ����ssh2��½�˿ڣ����ȡĬ��ֵ����-1
	 * @param privateKey ��Կ�ļ�·��
	 * @param passphrase ��Կ������
	 */
	public static void sshShell(String ip, String user, String psw, int port, String command) throws Exception {
		sshShell(ip, user, psw, port, command, null, null);
	}
	
	/**
	 * ����JSch��ʵ��Զ������SHELL����ִ��
	 * @param ip ����IP
	 * @param user ������½�û���
	 * @param psw  ������½����
	 * @param port ����ssh2��½�˿ڣ����ȡĬ��ֵ����-1
	 * @param privateKey ��Կ�ļ�·��
	 * @param passphrase ��Կ������
	 */
	public static void sshShell(String ip, String user, String psw, int port, String command, String privateKey, String passphrase)
			throws Exception {
		Session session = null;
		Channel channel = null;

		JSch jsch = new JSch();

		//������Կ������
		if (privateKey != null && !"".equals(privateKey)) {
			if (passphrase != null && "".equals(passphrase)) {
				//���ô��������Կ
				jsch.addIdentity(privateKey, passphrase);
			} else {
				//���ò����������Կ
				jsch.addIdentity(privateKey);
			}
        }

		if (port <= 0) {
			//���ӷ�����������Ĭ�϶˿�
			session = jsch.getSession(user, ip);
		} else {
			//����ָ���Ķ˿����ӷ�����
			session = jsch.getSession(user, ip, port);
		}

		//������������Ӳ��ϣ����׳��쳣
		if (session == null) {
			throw new Exception("session is null");
		}
		
		//���õ�½����������
		session.setPassword(psw);//��������   
		//���õ�һ�ε�½��ʱ����ʾ����ѡֵ��(ask | yes | no)
		session.setConfig("StrictHostKeyChecking", "no");
		//���õ�½��ʱʱ��   
		session.connect(30000);

		try {
			//����sftpͨ��ͨ��
			channel = (Channel) session.openChannel("shell");
			channel.connect(1000);

			//��ȡ�������������
			InputStream instream = channel.getInputStream();
			OutputStream outstream = channel.getOutputStream();

			//������Ҫִ�е�SHELL�����Ҫ��\n��β����ʾ�س�
			String shellCommand = command;
			outstream.write(shellCommand.getBytes());
			outstream.flush();

			Thread.sleep(1000);
			//��ȡ����ִ�еĽ��
			if (instream.available() > 0) {
				byte[] data = new byte[instream.available()];
				int nLen = instream.read(data);

				if (nLen < 0) {
					throw new Exception("network error.");
				}

				//ת������������ӡ����
				String temp = new String(data, 0, nLen, "utf-8");
				System.out.println(temp);
			}
			outstream.close();
			instream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.disconnect();
			channel.disconnect();
		}
	}
}
