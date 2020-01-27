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
public class result {
    String semester;
    int  stId;
    String course;
    int sec;
    String grade;

    public result(String semester, int stId, String course, int sec, String grade) {
        this.semester = semester;
        this.stId = stId;
        this.course = course;
        this.sec = sec;
        this.grade = grade;
    }

    public String getSemester() {
        return semester;
    }

    public int getStId() {
        return stId;
    }

    public String getCourse() {
        return course;
    }

    public int getSec() {
        return sec;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "result{" + "semester=" + semester + ", stId=" + stId + ", course=" + course + ", sec=" + sec + ", grade=" + grade + '}';
    }
    
    
    
    
}
