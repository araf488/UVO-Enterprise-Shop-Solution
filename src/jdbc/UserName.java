/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

/**
 *
 * @author Arefin
 */
public class UserName {

    String Id;
    String usrName;

    public UserName() {
    }

    public UserName(String Id) {
        this.Id = Id;
    }

    public UserName(String Id, String usrName) {
        this.Id = Id;
        this.usrName = usrName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
}
