package spring.dao3;

/**
 * 使用线程本地化信息，保存当前使用的DB信息
 * @author ly
 *
 */
public class DatabaseContextHolder {  
	  
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
  
    public static void setCustomerType(String customerType) {  
        contextHolder.set(customerType);  
    }  

    public static String getCustomerType() {
        return contextHolder.get();  
    }  
  
    public static void clearCustomerType() {
        contextHolder.remove();  
    }  
} 