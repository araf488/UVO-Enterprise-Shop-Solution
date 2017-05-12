package jdbc;
// Generated Mar 28, 2017 5:17:44 PM by Hibernate Tools 4.3.1



/**
 * Brand generated by hbm2java
 */
public class Brand  implements java.io.Serializable {


     private Integer id;
     private String suppliername;
     private String brandname;
     private String description;

    public Brand() {
    }

    public Brand(Integer id) {
        this.id = id;
    }

    public Brand(Integer id, String suppliername, String brandname, String description) {
        this.id = id;
        this.suppliername = suppliername;
        this.brandname = brandname;
        this.description = description;
    }

    public Brand(String suppliername, String brandname, String description) {
       this.suppliername = suppliername;
       this.brandname = brandname;
       this.description = description;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSuppliername() {
        return this.suppliername;
    }
    
    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }
    public String getBrandname() {
        return this.brandname;
    }
    
    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}

