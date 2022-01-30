package com.company.Users;

import com.company.ITreatmentCourseAssigner;
import com.company.TreatmentCourse;
import com.company.Users.Base.User;
import com.company.Users.UserInfo.UserGender;
import com.company.Users.UserInfo.UserName;

import java.util.List;

public class Surgeon extends User implements ITreatmentCourseAssigner {

    List<TreatmentCourse> _treatmentCourses;

    public Surgeon(UserName name, String emailAddress, String password, int age, UserGender userGender) {
        super(name, emailAddress, password, age, userGender);
    }

    public Surgeon(){

    }

    @Override
    public void AssignToTreatmentCourse(TreatmentCourse treatmentCourse) {
        if (!_treatmentCourses.contains(treatmentCourse))
            _treatmentCourses.add(treatmentCourse);
    }

    @Override
    public void UnAssignFromTreatmentCourse(TreatmentCourse treatmentCourse) {
        _treatmentCourses.remove(treatmentCourse);
    }
}
