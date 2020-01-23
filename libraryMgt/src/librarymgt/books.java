/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymgt;

/**
 *
 * @author User
 */
public class books {
    String bid;
    String bName;
    String bCat;
    int bCopy;

    public books(String bid, String bName, String bCat, int bCopy) {
        this.bid = bid;
        this.bName = bName;
        this.bCat = bCat;
        this.bCopy = bCopy;
    }

    public String getBid() {
        return bid;
    }

    public String getbName() {
        return bName;
    }

    public String getbCat() {
        return bCat;
    }

    public int getbCopy() {
        return bCopy;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public void setbCat(String bCat) {
        this.bCat = bCat;
    }

    public void setbCopy(int bCopy) {
        this.bCopy = bCopy;
    }

    @Override
    public String toString() {
        return "books{" + "bid=" + bid + ", bName=" + bName + ", bCat=" + bCat + ", bCopy=" + bCopy + '}';
    }

    
}
