package spring.dao1;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  

/**
 * 简化配置，通过动态DS，使得配置
 * @author ly
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{  
  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DatabaseContextHolder.getCustomerType();   
    }   
}  