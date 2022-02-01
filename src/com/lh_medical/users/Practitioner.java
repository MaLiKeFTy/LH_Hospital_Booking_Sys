package com.lh_medical.users;

import com.lh_medical.treatment_course.ITreatmentCourseAssigner;
import com.lh_medical.treatment_course.TreatmentCourse;
import com.lh_medical.users.base.User;
import com.lh_medical.users.user_info.UserGender;
import com.lh_medical.users.user_info.UserName;

import java.util.ArrayList;
import java.util.List;

public class Practitioner extends User implements ITreatmentCourseAssigner {

    List<TreatmentCourse> treatmentCourses = new ArrayList<>();

    public Practitioner(UserName name, String emailAddress, String password, int age, UserGender userGender) {
        super(name, emailAddress, password, age, userGender);
    }

    public Practitioner() {
    }

    @Override
    public void assignToTreatmentCourse(TreatmentCourse treatmentCourse) {
        if (!treatmentCourses.contains(treatmentCourse))
            treatmentCourses.add(treatmentCourse);
    }

    @Override
    public void unAssignFromTreatmentCourse(TreatmentCourse treatmentCourse) {

    }
}
