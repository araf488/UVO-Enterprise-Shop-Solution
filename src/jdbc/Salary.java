package jdbc;
// Generated Mar 28, 2017 5:17:44 PM by Hibernate Tools 4.3.1



/**
 * Salary generated by hbm2java
 */
public class Salary  implements java.io.Serializable {


     private Integer id;
     private String empname;
     private String position;
     private String salary;
     private String month;
     private String year;

    public Salary() {
    }

    public Salary(Integer id) {
        this.id = id;
    }

    public Salary(String empname, String position, String salary, String month, String year) {
       this.empname = empname;
       this.position = position;
       this.salary = salary;
       this.month = month;
       this.year = year;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmpname() {
        return this.empname;
    }
    
    public void setEmpname(String empname) {
        this.empname = empname;
    }
    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    public String getSalary() {
        return this.salary;
    }
    
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getMonth() {
        return this.month;
    }
    
    public void setMonth(String month) {
        this.month = month;
    }
    public String getYear() {
        return this.year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }




}


