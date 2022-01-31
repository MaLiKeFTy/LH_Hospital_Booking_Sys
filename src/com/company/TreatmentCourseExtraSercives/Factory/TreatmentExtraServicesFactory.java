package com.company.TreatmentCourseExtraSercives.Factory;

import com.company.TreatmentCourseExtraSercives.Base.TreatmentCourseExtraService;
import com.company.TreatmentCourseExtraSercives.*;

import java.util.ArrayList;
import java.util.List;

public final class TreatmentExtraServicesFactory {


    public static TreatmentCourseExtraService GetTreatmentExtraService(String extraService) {

        String[] treatmentExtraServiceNames = TreatmentExtraServiceNames();

        for (int i = 0; i < treatmentExtraServiceNames.length; i++) {

            String treatmentExtraServiceName = treatmentExtraServiceNames[i];

            if (treatmentExtraServiceName.contains(extraService)) {
                return TreatmentExtraServices()[i];
            }
        }

        return null;
    }

    public static TreatmentCourseExtraService[] TreatmentExtraServices() {

        TreatmentCourseExtraService[] treatmentExtraServices = {
                new ClinicalReportsAndAttachedDocumentsAndHistoryService(),
                new ConsultationsAndCheckUpService(),
                new MedicineAndDifferentTypesOfTablesService(),
                new OperationsAndVaccinationsService(),
                new PrescriptionsAndRepeatPrescriptions()
        };

        return treatmentExtraServices;
    }


    public static String[] TreatmentExtraServiceNames() {
        List<String> tempTreatmentExtraServices = new ArrayList<>();

        for (TreatmentCourseExtraService treatmentExtraService : TreatmentExtraServices()) {
            tempTreatmentExtraServices.add(treatmentExtraService.getClass().getSimpleName());
        }

        return tempTreatmentExtraServices.toArray(new String[0]);
    }

}
