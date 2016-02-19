package spring.dao1.two;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import spring.dao1.po.City;
  
@Repository  
public class CityDaoImpl  {  
  
    @Resource  
    private SessionFactory sessionFactory;  
  
    @SuppressWarnings("unchecked")
    public List<City> find() {  
        String hql = "from City";  
        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
        return query.list();  
    }  
}  