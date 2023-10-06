package ClinicManagement;

import java.io.Serializable;
import java.util.*;
import ClinicManagement.Doctor.DoctorTypes;

public class Admin implements Serializable {
    private HashMap<DoctorTypes, ArrayList<Doctor>> doctorsList;
    private ArrayList<Receptionist> receptionistsList;
    private ArrayList<Cashier> cashiersList;
    private HashMap<UUID, Patient> patientsList;
    private HashMap<UUID, Patient> patientsInQueue;


    Admin() {
        this.doctorsList = new HashMap();
        this.receptionistsList = new ArrayList();
        this.cashiersList = new ArrayList();
        this.patientsList = new HashMap();
        this.patientsInQueue = new HashMap();
    }

    Doctor addDoctor(String name, DoctorTypes type) {
        UUID doctorId = UUID.randomUUID();
        Doctor doctor;
        switch (type) {
            case NEUROLOGIST:
                doctor = new Neurologist(doctorId, name, this);
                break;
            case CANCER_SPECIALIST:
                doctor = new CancerSpecialist(doctorId, name, this);
                break;
            default:
                doctor = new Doctor(doctorId, name, this);
                break;
        }

        if(this.doctorsList.containsKey(type)) {
            this.doctorsList.get(type).add(doctor);
        } else {
            ArrayList<Doctor> specializationList = new ArrayList<Doctor>();
            specializationList.add(doctor);
            this.doctorsList.put(type, specializationList);
        }

        return doctor;
    }

    Receptionist addReceptionist(String name) {
        Receptionist receptionist = new Receptionist(UUID.randomUUID(), name);
        receptionistsList.add(receptionist);

        return receptionist;
    }

    Cashier addCashier(String name) {
        Cashier cashier = new Cashier(UUID.randomUUID(), name, this);
        cashiersList.add(cashier);

        return cashier;
    }

    Doctor selectDoctor(DoctorTypes type) {
        ArrayList<Doctor> doctorsWithProperSpecialization = doctorsList.get(type);
        Doctor selectedDoctor = doctorsWithProperSpecialization.get(new Random().nextInt(doctorsWithProperSpecialization.size()));
        return selectedDoctor;
    }

    void startPatientTreatmentFlow(Patient patient) {
        try {
            Receptionist selectedReceptionist = receptionistsList.get(new Random().nextInt(receptionistsList.size()));
            selectedReceptionist.receipt(patient);
            Cashier selectedCashier = cashiersList.get(new Random().nextInt(cashiersList.size()));
            selectedCashier.startPatientFlow(patient);
            patientsList.put(patient.getId(), patient);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void addPatientToDoctorsQueue(Patient patient, Doctor doctor) {
        doctor.addPatient(patient);
        this.patientsInQueue.put(patient.getId(), patient);
    }

    public void removePatientFromPatientsQueue(Patient patient) {
        UUID patientId = patient.getId();
        patientsInQueue.remove(patientId);
    }
    
    public void payDoctorSalary(Doctor doctor) {
        Cashier selectedCashier = cashiersList.get(new Random().nextInt(cashiersList.size()));
        selectedCashier.payDoctorSalary(doctor);
    }

    public void logInQueuePatients() {
        System.out.println(this.patientsInQueue);
    }
    
    public void logAllPatients() {
        System.out.println("Patients list:");
        System.out.println(this.patientsList);
    }
    
    public void logDoctors() {
        ArrayList<Doctor> doctors = new ArrayList();
        this.doctorsList.values().forEach((specialists) -> doctors.addAll(specialists));
        System.out.println("Doctors list:");
        System.out.println(doctors);
    }
    
    public void logReceptionists() {
        System.out.println("Receptionists list:");
        System.out.println(this.receptionistsList);
    }
    
    public void logCashiersList() {
        System.out.println("Cashiers list:");
        System.out.println(this.cashiersList);
    }
    
    public void logAllEmployees() {
        this.logDoctors();
        this.logReceptionists();
        this.logCashiersList();
    }
    
    public void logDoctorInfo(UUID doctorId) {
        ArrayList<Doctor> doctors = new ArrayList();
        this.doctorsList.values().forEach((specialists) -> doctors.addAll(specialists));
        for(int i=0; i < doctors.size();i++){
                if(doctors.get(i).getId() == doctorId){
                    System.out.println(doctors.get(i));
                    break;
                }
        }
    }
    
    public void logPatientInfo(UUID patientId) {
        System.out.println(this.patientsList.get(patientId));
    }
}
