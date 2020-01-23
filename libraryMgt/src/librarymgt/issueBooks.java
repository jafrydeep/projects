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
public class issueBooks {
    int sId;
    String sName;
    String sDept;
    String bId;
    String bName;
    String bCat;
    String type;
    Date issueDate;

    public issueBooks(int sId, String sName, String sDept, String bId, String bName, String bCat, String type, Date issueDate) {
        this.sId = sId;
        this.sName = sName;
        this.sDept = sDept;
        this.bId = bId;
        this.bName = bName;
        this.bCat = bCat;
        this.type = type;
        this.issueDate = issueDate;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsDept() {
        return sDept;
    }

    public void setsDept(String sDept) {
        this.sDept = sDept;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbCat() {
        return bCat;
    }

    public void setbCat(String bCat) {
        this.bCat = bCat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "issueBooks{" + "sId=" + sId + ", sName=" + sName + ", sDept=" + sDept + ", bId=" + bId + ", bName=" + bName + ", bCat=" + bCat + ", type=" + type + ", issueDate=" + issueDate + '}';
    }
    
    
    
}
