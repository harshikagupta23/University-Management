/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CourseOffer {

    Course course;
    ArrayList<Seat> seatlist;
    FacultyAssignment facultyassignment;
    String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    

    public CourseOffer(Course c) {
        course = c;
        seatlist = new ArrayList();
    }
    public void AssignAsTeacher(FacultyProfile fp) {

        facultyassignment = new FacultyAssignment(fp, this);
    }

    public FacultyProfile getFacultyProfile() {
        return facultyassignment.getFacultyProfile();
    }

    public String getCourseNumber() {
        return course.getCOurseNumber();
    }

    public void generatSeats(int n) {

        for (int i = 0; i < n; i++) {

            seatlist.add(new Seat(this, i));

        }

    }

    public Seat getEmptySeat() {
        System.out.println(seatlist.size() + " list size ");
        for (Seat s : seatlist) {
            if (!s.isOccupied()) {
                return s;
            }
        }
        return null;
    }

    public SeatAssignment assignEmptySeat(CourseLoad cl) {

        Seat seat = getEmptySeat();
        if (seat == null) {
            return null;
        }
        SeatAssignment sa = seat.newSeatAssignment(); //seat is already linked to course offer
        cl.registerStudent(sa);; //coures offer seat is now linked to student
        return sa;
    }

    public int getTotalCourseRevenues   () {

        int sum = 0;

        for (Seat s : seatlist) {
            if (s.isOccupied() == true) {
                sum = sum + course.getCoursePrice();
            }

        }
        return sum;
    }
    
    public int getTotalCourseLoss() {
        int sum = 0;
        for (Seat s : seatlist) {
            if (s.isOccupied() == false) {
                sum = sum + course.getCoursePrice();
            }
        }
        return sum;
    }
    
    public int getTotalSeats() {
        return seatlist.size();
    }
    
    public int getOccupiedSeats() {
        int sum = 0;
        for (Seat s : seatlist) {
            if (s.isOccupied() == true) {
                sum += 1;
            }
        }
        return sum;
    }

}
