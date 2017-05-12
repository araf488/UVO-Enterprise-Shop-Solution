package jdbc;
// Generated Mar 28, 2017 5:17:44 PM by Hibernate Tools 4.3.1



/**
 * Supplier generated by hbm2java
 */
public class Supplier  implements java.io.Serializable {


     private Integer id;
     private String suppliername;
     private String contactno;
     private String address;
     private String suppsince;

    public Supplier() {
    }

    public Supplier(Integer id, String suppliername, String contactno, String address, String suppsince) {
        this.id = id;
        this.suppliername = suppliername;
        this.contactno = contactno;
        this.address = address;
        this.suppsince = suppsince;
    }

    public Supplier(Integer id) {
        this.id = id;
    }

    public Supplier(String suppliername, String contactno, String address, String suppsince) {
       this.suppliername = suppliername;
       this.contactno = contactno;
       this.address = address;
       this.suppsince = suppsince;
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
    public String getContactno() {
        return this.contactno;
    }
    
    public void setContactno(String contactno) {
        this.contactno = contactno;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSuppsince() {
        return this.suppsince;
    }
    
    public void setSuppsince(String suppsince) {
        this.suppsince = suppsince;
    }




}


