package ClinicManagement;

import ClinicManagement.Doctor.DoctorTypes;
import ClinicManagement.Patient.PatientStatusTypes;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.UUID;

public class Receptionist implements Serializable {
    private final UUID id;
    private final String name;
    static EnumMap<PatientIllnessType , DoctorTypes> IllnessDoctorMap = new EnumMap<PatientIllnessType, DoctorTypes>(PatientIllnessType.class) {
        {
            put(PatientIllnessType.CANCER, DoctorTypes.CANCER_SPECIALIST);
            put(PatientIllnessType.COLD, DoctorTypes.GENERAL);
            put(PatientIllnessType.HEADACHE, DoctorTypes.NEUROLOGIST);
        }

    };

    Receptionist(UUID id, String name) {
       this.id = id;
       this.name = name;
    }

    void receipt(Patient patient) throws Exception {
        PatientIllnessType patientIllnessType = patient.getIllnessType();
        if(!Receptionist.IllnessDoctorMap.containsKey(patientIllnessType)) {
            throw new Exception("Your requested service is not provided in this clinic.");
        } else {
            DoctorTypes properDoctorType = Receptionist.IllnessDoctorMap.get(patientIllnessType);
            patient.setStatus(PatientStatusTypes.ACCEPTED);
            patient.setAllocatedDoctorType(properDoctorType);
        }
    }
    
    public UUID getId() {
        return id;
    }
}
