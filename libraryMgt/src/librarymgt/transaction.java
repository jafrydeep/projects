/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymgt;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class transaction {
    int sid ;
    String sname;
    String sdept;
    String bid;
    String bname;
    String bcat;
    String type;
    Date date;

    public transaction(int sid, String sname, String sdept, String bid, String bname, String bcat, String type, Date date) {
        this.sid = sid;
        this.sname = sname;
        this.sdept = sdept;
        this.bid = bid;
        this.bname = bname;
        this.bcat = bcat;
        this.type = type;
        this.date = date;
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

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
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

    public String getBcat() {
        return bcat;
    }

    public void setBcat(String bcat) {
        this.bcat = bcat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "transaction{" + "sid=" + sid + ", sname=" + sname + ", sdept=" + sdept + ", bid=" + bid + ", bname=" + bname + ", bcat=" + bcat + ", type=" + type + ", date=" + date + '}';
    }

    
    
    
}
