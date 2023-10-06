package ClinicManagement;

import ClinicManagement.Patient.PatientStatusTypes;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class Doctor implements Serializable {
    private final UUID id;
    private final String name;
    protected DoctorTypes type;
    private Queue<Patient> patientsQueue;
    private Admin admin;

    Doctor(UUID id, String name, Admin admin) {
        this.id = id;
        this.name = name;
        this.patientsQueue = new LinkedList<Patient>();
        this.type = DoctorTypes.GENERAL;
        this.admin = admin;
    }
    
    static enum DoctorTypes {
        GENERAL,
        CANCER_SPECIALIST,
        NEUROLOGIST,
    }

    static boolean isSpecialist(DoctorTypes type) {
        return type != DoctorTypes.GENERAL;
    }

    void visit() {
        Patient firstPatient = this.patientsQueue.remove();
        System.out.println(String.format("Doctor #%s visiting patient #%s", this.getId(), firstPatient.getId()));
        firstPatient.setStatus(PatientStatusTypes.VISITED);
        this.admin.removePatientFromPatientsQueue(firstPatient);
    }

    boolean addPatient(Patient patient) {
        this.patientsQueue.add(patient);
        patient.setStatus(PatientStatusTypes.AWAIT);

        return true;
    }

    public UUID getId() {
        return id;
    }

    public DoctorTypes getType() {
        return type;
    }
    
    public void logQueue() {
        System.out.println(String.format("Doctor #%S queue:", this.id));
        System.out.println(this.patientsQueue);
    }
}
