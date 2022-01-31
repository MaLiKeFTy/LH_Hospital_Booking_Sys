package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class TreatmentCourse {

    int _id;
    String _name;
    TreatmentCourseStatus _status = TreatmentCourseStatus.ONGOING;

    public TreatmentCourse(String name) {
        set_id(ThreadLocalRandom.current().nextInt(10, 9999 + 1));
        set_name(name);
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public TreatmentCourseStatus get_status() {
        return _status;
    }

    public void set_status(TreatmentCourseStatus _status) {
        this._status = _status;
    }
}
