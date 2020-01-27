/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesystem;

/**
 *
 * @author JAFRY
 */
public class dept {
    
    int dId;
    String dName;
    String dLoc;
    int dPhn;

    public dept(int dId, String dName, String dLoc, int dPhn) {
        this.dId = dId;
        this.dName = dName;
        this.dLoc = dLoc;
        this.dPhn = dPhn;
    }

    public int getdId() {
        return dId;
    }

    public String getdName() {
        return dName;
    }

    public String getdLoc() {
        return dLoc;
    }

    public int getdPhn() {
        return dPhn;
    }

    @Override
    public String toString() {
        return "dept{" + "dId=" + dId + ", dName=" + dName + ", dLoc=" + dLoc + ", dPhn=" + dPhn + '}';
    }
    
}
