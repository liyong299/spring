package com.ly.test.spring.ftp�ϴ�;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ����ѯ���� {

	static String destDir = "/home/cec/cec_slow/zy/";
	static String localDir = "E:/01_work/02_cec/03_����/02_��Ӱ/03_1017��SQL����/20160906/";
	static String localFileName = "DAT00000.bin";
	static String destFileName = localFileName;
	static String hostInfo = "cec:cec@172.16.34.48:2235";

	public static void main(String[] args) {
		// �ϴ��ļ���
		uploadSlowLog();
		// ִ�з�������

		// ���ط������

	}

	private static void uploadSlowLog() {
		Sftp sftp = getSftp();
		sftp.login();
		try {
			sftp.changeDir(destDir);
			String destDirTmp = destDir + getStringDateShort();
			sftp.makeDir(destDirTmp);
			sftp.uploadFile(destDirTmp, destFileName, localDir + localFileName);
			destDir = destDirTmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		sftp.logout();
	}

	private static Sftp getSftp() {
		String host = hostInfo.substring(hostInfo.indexOf('@') + 1, hostInfo.lastIndexOf(':'));
		int port = Integer.valueOf(hostInfo.substring(hostInfo.lastIndexOf(':') + 1));
		int timeout = 60000;
		String username = hostInfo.substring(0, hostInfo.indexOf(':'));
		String password = hostInfo.substring(hostInfo.indexOf(':') + 1, hostInfo.indexOf('@'));

		System.out.println(String.format("��ַ��%s ,�˿ڣ�%s ,�û�����%s ,���룺%s", host, port + "", username, password));
		Sftp sftp = new Sftp(host, port, timeout, username, password);

		return sftp;
	}

	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
}
