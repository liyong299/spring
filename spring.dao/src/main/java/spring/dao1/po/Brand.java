package spring.dao1.po;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.Id;
import javax.persistence.Table;  
  
@Entity  
@Table(name = "BTSF_BRAND", schema = "stat2")  
public class Brand {  
  
    private String id;  
    private String names;  
    private String url;  
  
    @Id  
    @Column(name = "ID", unique = true, nullable = false, length = 10)  
    public String getId() {  
        return this.id;  
    }  
  
    public void setId(String id) {  
        this.id = id;  
    }  
  
    @Column(name = "NAMES", nullable = false, length = 50)  
    public String getNames() {  
        return this.names;  
    }  
  
    public void setNames(String names) {  
        this.names = names;  
    }  
  
    @Column(name = "URL", length = 200)  
    public String getUrl() {  
        return this.url;  
    }  
  
    public void setUrl(String url) {  
        this.url = url;  
    }  
}  