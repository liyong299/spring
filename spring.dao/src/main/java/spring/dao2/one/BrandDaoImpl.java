package spring.dao2.one;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.dao1.po.Brand;

  
@Repository  
public class BrandDaoImpl
{

    
	@Resource
    private JdbcTemplate jdbcTemplate;
  
    public List<Map<String, Object>> findAll() {
        String hql = "select * from BTSF_BRAND";  
        return this.jdbcTemplate.queryForList(hql);  
    }  
}