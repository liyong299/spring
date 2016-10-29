package com.ly.test.spring.ftp上传;

import java.text.SimpleDateFormat;
import java.util.Date;


public class 慢查询处理 {

	static String destDir = "/home/cec/cec_slow/zy/";
	static String localDir = "E:/01_work/02_cec/03_需求/02_中影/03_1017慢SQL分析/20160906/";
	static String localFileName = "DAT00000.bin";
	static String destFileName = localFileName;
	static String hostInfo = "cec:cec@172.16.34.48:2235";

	public static void main(String[] args) {
		// 上传文件。
		uploadSlowLog();
		// 执行分析命令

		// 下载分析结果

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

		System.out.println(String.format("地址：%s ,端口：%s ,用户名：%s ,密码：%s", host, port + "", username, password));
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
