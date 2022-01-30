package com.company.Users;

import com.company.ITreatmentCourseAssigner;
import com.company.TreatmentCourse;
import com.company.Users.Base.User;
import com.company.Users.UserInfo.*;

import java.util.ArrayList;
import java.util.List;

public class Client extends User implements ITreatmentCourseAssigner {

    List<TreatmentCourse> _treatmentCourses;

    List<Practitioner> _practitioners = new ArrayList<>();


    public Client(UserName name, String emailAddress, String password, int age, UserGender userGender) {
        super(name, emailAddress, password, age, userGender);
    }

    public Client(){

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

    public List<Practitioner> get_practitioners() {
        return _practitioners;
    }

    public void set_practitioners(List<Practitioner> _practitioners) {
        this._practitioners = _practitioners;
    }
}
