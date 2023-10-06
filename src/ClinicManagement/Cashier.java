package ClinicManagement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import ClinicManagement.Doctor.DoctorTypes;

public class Cashier implements Serializable {
    private final UUID id;
    private final String name;
    private Admin admin;
    static private HashMap<UUID, Integer> doctorIdsPatientCountMap;
    static private Integer generalDoctorSalary = 320;
    static private Integer specialistDoctorSalary = 540;
    static private float doctorIncomePercentageRate = 0.5f;
    
    Cashier(UUID id, String name, Admin admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.doctorIdsPatientCountMap = new HashMap<UUID, Integer>();
    }
    
    public UUID getId() {
        return id;
    }

    public static float createBill(DoctorTypes allocatedDoctorType) {
        if(Doctor.isSpecialist(allocatedDoctorType)) {
            return Cashier.specialistDoctorSalary;
        }

        return Cashier.generalDoctorSalary;
    }

    public void startPatientFlow(Patient patient) {
        DoctorTypes doctorType = patient.getAllocatedDoctorType();
        float bill = this.createBill(doctorType);
        if(!patient.payBill(bill)) {
            throw new Error("Your payment was not successful, please try again later.");
        }

        Doctor doctor = this.admin.selectDoctor(doctorType);
        UUID doctorId = doctor.getId();
        if(doctorIdsPatientCountMap.containsKey(doctorId)) {
            doctorIdsPatientCountMap.put(doctorId, doctorIdsPatientCountMap.get(doctorId) + 1);
        } else {
            doctorIdsPatientCountMap.put(doctorId, 1);
        }

        this.admin.addPatientToDoctorsQueue(patient, doctor);
    }

    void payDoctorSalary(Doctor doctor) {
        UUID doctorId = doctor.getId();
        if(!doctorIdsPatientCountMap.containsKey(doctorId)) {
            System.out.println(String.format("Amount of $0 paid to Doctor #%s", doctorId.toString()));
        } else {
            float DoctorSalary = Doctor.isSpecialist(doctor.getType()) ? Cashier.specialistDoctorSalary : Cashier.generalDoctorSalary;
            Integer patientsCounter = doctorIdsPatientCountMap.get(doctorId);
            float toPay = DoctorSalary * patientsCounter * Cashier.doctorIncomePercentageRate;
            System.out.println(String.format("Amount of %s$ paid to Doctor #%s", toPay, doctorId.toString()));
            doctorIdsPatientCountMap.put(doctorId, 0);
        }
    }
    
}
