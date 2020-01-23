/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymgt;

import java.sql.Date;

/**
 *
 * @author User
 */
public class returnbook {
    int sid;
    String sname;
    String bid;
    String bname;
    String type;
    String idate;
    Date rdate;

    public returnbook(int sid, String sname, String bid, String bname, String type, String idate, Date rdate) {
        this.sid = sid;
        this.sname = sname;
        this.bid = bid;
        this.bname = bname;
        this.type = type;
        this.idate = idate;
        this.rdate = rdate;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    @Override
    public String toString() {
        return "returnbook{" + "sid=" + sid + ", sname=" + sname + ", bid=" + bid + ", bname=" + bname + ", type=" + type + ", idate=" + idate + ", rdate=" + rdate + '}';
    }

    
    
    
}
