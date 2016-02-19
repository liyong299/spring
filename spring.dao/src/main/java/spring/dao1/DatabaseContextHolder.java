package spring.dao1;

/**
 * ʹ���̱߳��ػ���Ϣ�����浱ǰʹ�õ�DB��Ϣ
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