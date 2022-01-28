package com.company.Users;

import com.company.ITreatmentCourseAssigner;
import com.company.TreatmentCourse;
import com.company.Users.Base.User;
import com.company.Users.UserInfo.*;

import java.util.List;

public class Client extends User implements ITreatmentCourseAssigner {

    List<TreatmentCourse> _treatmentCourses;

    public Client(UserName name, String emailAddress, String password, int age, UserGender userGender) {
        super(name, emailAddress, password, age, userGender);
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
