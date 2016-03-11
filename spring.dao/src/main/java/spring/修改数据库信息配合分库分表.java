/**
 * 项目名称：spring.dao
 * 文件包名：spring
 * 文件名称：修改数据库信息配合分库分表.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月7日 上午10:51:43
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：修改数据库信息配合分库分表.java
 * @author ly
 */
public class 修改数据库信息配合分库分表
{
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;  
    
	/**
	 * 功能描述：<p>方法功能</p>
	 * 实现逻辑：<p>实现步骤</p>
	 * @param args
	 */
	public static void main(String[] args)
	{
		test1修改所有库的指定表名();
	}

	public static void test1修改所有库的指定表名()
	{
		sql = "rename table dist_scec1_0.CEC_TicketOrder_0 to dist_scec1_0.CEC_TicketOrder_201601";//SQL语句  
        db1 = new DBHelper(sql);//创建DBHelper对象  
	}
}

class DBHelper 
{  
    public static final String url = "jdbc:mysql://172.16.34.12:3306/dist_scec1_0";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DBHelper(String sql) 
    {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        finally
        {
        	close();
        }
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}  
