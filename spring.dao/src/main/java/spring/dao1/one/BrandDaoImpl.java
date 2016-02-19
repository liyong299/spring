package spring.dao1.one;

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
    protected SessionFactory sessionFactory;  
  
    @SuppressWarnings("unchecked")  
    public List<Brand> findAll() {
        String hql = "from Brand";  
        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
        return query.list();  
    }  
}