/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesystem;

/**
 *
 * @author Lenovo
 */
public class course {
    String cCode;
    String cName;
    int cSec;
    int cCredit;

    public course(String cCode, String cName, int cSec, int cCredit) {
        this.cCode = cCode;
        this.cName = cName;
        this.cSec = cSec;
        this.cCredit = cCredit;
    }

    public String getcCode() {
        return cCode;
    }

    public String getcName() {
        return cName;
    }

    public int getcSec() {
        return cSec;
    }

    public int getcCredit() {
        return cCredit;
    }

    @Override
    public String toString() {
        return "course{" + "cCode=" + cCode + ", cName=" + cName + ", cSec=" + cSec + ", cCredit=" + cCredit + '}';
    }
    
    
}
