package com.lh_medical.treatment_course.treatment_course_extra_sercives.factory;

import com.lh_medical.treatment_course.treatment_course_extra_sercives.base.treatmentCourseExtraService;
import com.lh_medical.treatment_course.treatment_course_extra_sercives.*;

import java.util.ArrayList;
import java.util.List;

public final class TreatmentExtraServicesFactory {


    public static treatmentCourseExtraService GetTreatmentExtraService(String extraService) {

        String[] treatmentExtraServiceNames = treatmentExtraServiceNames();

        for (int i = 0; i < treatmentExtraServiceNames.length; i++) {

            String treatmentExtraServiceName = treatmentExtraServiceNames[i];

            if (treatmentExtraServiceName.contains(extraService)) {
                return TreatmentExtraServices()[i];
            }
        }

        return null;
    }

    public static treatmentCourseExtraService[] TreatmentExtraServices() {

        treatmentCourseExtraService[] treatmentExtraServices = {
                new ClinicalReportsAndAttachedDocumentsAndHistoryService(),
                new ConsultationsAndCheckUpService(),
                new MedicineAndDifferentTypesOfTablesService(),
                new OperationsAndVaccinationsService(),
                new PrescriptionsAndRepeatPrescriptions()
        };

        return treatmentExtraServices;
    }


    public static String[] treatmentExtraServiceNames() {
        List<String> tempTreatmentExtraServices = new ArrayList<>();

        for (treatmentCourseExtraService treatmentExtraService : TreatmentExtraServices()) {
            tempTreatmentExtraServices.add(treatmentExtraService.getClass().getSimpleName());
        }

        return tempTreatmentExtraServices.toArray(new String[0]);
    }

}
