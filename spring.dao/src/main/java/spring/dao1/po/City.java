package spring.dao1.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
  
@Entity  
@Table(name = "CITY", schema = "stat")  
public class City {  
  
    private Integer id;  
      
    private String name;  
  
    @Id  
    @Column(name = "ID", unique = true, nullable = false)  
    public Integer getId() {  
        return id;  
    }  
  
    public void setId(Integer id) {  
        this.id = id;  
    }  
  
    @Column(name = "NAME", nullable = false, length = 50)  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
}