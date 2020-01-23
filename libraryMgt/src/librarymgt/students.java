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
public class students {
    
      int id;
    String name;
    String loc;
    String dept;
    String batch;

    public students(int id, String name, String loc, String dept, String batch) {
        this.id = id;
        this.name = name;
        this.loc = loc;
        this.dept = dept;
        this.batch = batch;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLoc() {
        return loc;
    }

    public String getDept() {
        return dept;
    }

    public String getBatch() {
        return batch;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "student{" + "id=" + id + ", name=" + name + ", loc=" + loc + ", dept=" + dept + ", batch=" + batch + '}';
    }
    
}
