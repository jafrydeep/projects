/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesystem;

import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class student {
    int id;
    String name;
    String mail;
    LocalDate dob;
    String loc;
    int phn;
    String dept;

    public student(int id, String name, String mail, LocalDate dob, String loc, int phn, String dept) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.dob = dob;
        this.loc = loc;
        this.phn = phn;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getLoc() {
        return loc;
    }

    public int getPhn() {
        return phn;
    }

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "student{" + "id=" + id + ", name=" + name + ", mail=" + mail + ", dob=" + dob + ", loc=" + loc + ", phn=" + phn + ", dept=" + dept + '}';
    }
    
    
    
}
