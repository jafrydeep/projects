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
public class Cregister {
    String semId;
    int sId;
    String cCode;
    int cSec;

    public Cregister(String semId, int sId, String cCode, int cSec) {
        this.semId = semId;
        this.sId = sId;
        this.cCode = cCode;
        this.cSec = cSec;
    }

    public String getSemId() {
        return semId;
    }

    public int getsId() {
        return sId;
    }

    public String getcCode() {
        return cCode;
    }

    public int getcSec() {
        return cSec;
    }

    @Override
    public String toString() {
        return "Cregister{" + "semId=" + semId + ", sId=" + sId + ", cCode=" + cCode + ", cSec=" + cSec + '}';
    }
    
    
    
}
