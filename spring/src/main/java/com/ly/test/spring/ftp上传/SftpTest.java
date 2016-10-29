package com.ly.test.spring.ftpÉÏ´«;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;

public class SftpTest {

	@Test
	public void testLogin() { //OK
		Sftp sftp = getSftp();

		sftp.login();
		sftp.logout();
	}

	@Test
	public void testMakeDir() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.makeDir("test2");
		sftp.changeDir("test2");
		sftp.makeDir("/test2/test2_1");
		sftp.logout();
	}

	@Test
	public void testDelFile() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.delFile("file1.txt");
		sftp.logout();
	}

	@Test
	public void testDelEmptyDir() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.delDir("test3");
		sftp.logout();
	}

	@Test
	public void testDir() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.delDir("test4");
		sftp.logout();
	}

	@Test
	public void testLs() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		System.out.println(Arrays.toString(sftp.ls()));
		System.out.println(Arrays.toString(sftp.lsFiles()));
		System.out.println(Arrays.toString(sftp.lsDirs()));
		sftp.logout();
	}

	@Test
	public void testParamLs() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		System.out.println(Arrays.toString(sftp.ls("test1/test4")));
		System.out.println(Arrays.toString(sftp.lsFiles("test1/test4")));
		System.out.println(Arrays.toString(sftp.lsDirs("test1/test4")));
		sftp.logout();
	}

	@Test
	public void testChangeDir() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.changeDir("test1");
		sftp.changeDir("/test1/test4");
		sftp.changeToParentDir();
		sftp.changeToHomeDir();
		sftp.logout();
	}

	@Test
	public void testExist() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		System.out.println(sftp.exist("2fs.docx"));
		System.out.println(sftp.exist("test1"));
		System.out.println(sftp.existDir("test2"));
		System.out.println(sftp.existDir("2sfs.txt"));
		System.out.println(sftp.existFile("2sfs.txt"));
		System.out.println(sftp.existFile("test2"));
		sftp.logout();
	}

	@Test
	public void testParamExist() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		System.out.println(sftp.exist("test1", "test4"));
		System.out.println(sftp.exist("test1", "test_bak.jpg"));
		System.out.println(sftp.existDir("/test1", "test3"));
		System.out.println(sftp.existDir("/test1", "test_bak.jpg"));
		System.out.println(sftp.existFile("test1", "test_bak.jpg"));
		System.out.println(sftp.existFile("test1", "test2"));
		sftp.logout();
	}

	@Test
	public void testUploadFile() { //OK
		Sftp sftp = getSftp();
		sftp.login();

		try {
			sftp.uploadFile("/home/cec/cec_slow/zy", "bb.txt", "aa.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sftp.logout();
	}

	@Test
	public void testUploadFile2() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.uploadFile("test1/test3", "test_bak2.jpg", "D:\\test.jpg");
		try {
			sftp.uploadFile("test1/test2", "test_bak3.jpg", new FileInputStream("D:\\test.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sftp.logout();
	}

	@Test
	public void testDownload() { //OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.downloadFile("test1", "test_bak.jpg", "D:\\testdown");
		sftp.downloadFile("/", "2fs.docx", "D:\\testdown");
		sftp.logout();
	}

	private Sftp getSftp() {

		String host = "172.16.34.48";
		int port = 2235;
		int timeout = 60000;
		String username = "cec";
		String password = "cec";

		Sftp sftp = new Sftp(host, port, timeout, username, password);

		return sftp;
	}
}