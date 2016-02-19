package spring.dao2.two;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Repository;
  
@Repository  
public class CityDaoImpl  {  
  
  
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    public List<Map<String, Object>> find() {  
        String hql = "select * from CITY";
//        this.jdbcTemplate.setDataSource(new AbstractRoutingDataSource()
//		{
//			@Override
//			protected Object determineCurrentLookupKey()
//			{
//				return "dataSourceOne";
//			}
//		});
        
        return this.jdbcTemplate.queryForList(hql);  
    }  
}  