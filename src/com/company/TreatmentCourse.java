package com.company;

import com.company.TreatmentCourseExtraSercives.Base.TreatmentCourseExtraService;

import java.util.ArrayList;
import java.util.List;

public class TreatmentCourse {

    String _name;
    TreatmentCourseStatus _status = TreatmentCourseStatus.ONGOING;
    List<TreatmentCourseExtraService> _extraServices = new ArrayList<>();

    public TreatmentCourse(String name, TreatmentCourseStatus status, List<TreatmentCourseExtraService> extraServices) {
        _name = name;
        _status = status;
        _extraServices = extraServices;
    }

    public void ChangeTreatmentCourseStatus(TreatmentCourseStatus status) {
        _status = status;
    }

    public void AddExtraService(TreatmentCourseExtraService extraService) {
        if (!_extraServices.contains(extraService))
            _extraServices.add(extraService);
    }

    public void RemoveExtraService(TreatmentCourseExtraService extraService) {
        _extraServices.remove(extraService);
    }

}
