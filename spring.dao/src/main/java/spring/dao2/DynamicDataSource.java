package spring.dao2;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  

/**
 * �����ã�ͨ����̬DS��ʹ������
 * @author ly
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{  
  
    @Override  
    protected Object determineCurrentLookupKey() {
    	System.out.println("��ǰ��DS : " + DatabaseContextHolder.getCustomerType());
        return DatabaseContextHolder.getCustomerType();
    }   
}  