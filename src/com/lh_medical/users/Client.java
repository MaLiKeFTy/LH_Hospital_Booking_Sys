package com.lh_medical.users;

import com.lh_medical.treatment_course.ITreatmentCourseAssigner;
import com.lh_medical.treatment_course.TreatmentCourse;
import com.lh_medical.users.base.User;
import com.lh_medical.users.user_info.*;

import java.util.List;

public class Client extends User implements ITreatmentCourseAssigner {

    List<TreatmentCourse> treatmentCourses;

    Practitioner[] practitioners;


    public Client(UserName name, String emailAddress, String password, int age, UserGender userGender) {
        super(name, emailAddress, password, age, userGender);
    }

    public Client(){

    }

    @Override
    public void assignToTreatmentCourse(TreatmentCourse treatmentCourse) {
        if (!treatmentCourses.contains(treatmentCourse))
            treatmentCourses.add(treatmentCourse);
    }

    @Override
    public void unAssignFromTreatmentCourse(TreatmentCourse treatmentCourse) {
        treatmentCourses.remove(treatmentCourse);
    }

    public Practitioner[] getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(Practitioner[] practitioners) {
        this.practitioners = practitioners;
    }
}
