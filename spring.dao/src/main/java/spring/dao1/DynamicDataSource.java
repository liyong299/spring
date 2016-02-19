package spring.dao1;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  

/**
 * �����ã�ͨ����̬DS��ʹ������
 * @author ly
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{  
  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DatabaseContextHolder.getCustomerType();   
    }   
}  