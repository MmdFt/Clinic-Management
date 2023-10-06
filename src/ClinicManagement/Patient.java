package ClinicManagement;

import java.io.Serializable;
import java.util.UUID;

enum PatientIllnessType {
    COLD,
    HEADACHE,
    CANCER,
}

public class Patient implements Serializable {
    private final UUID id;
    private final String name;
    private PatientIllnessType illnessType;
    private PatientStatusTypes status;
    private Doctor.DoctorTypes allocatedDoctorType;
    
    static enum PatientStatusTypes {
        DEFAULT,
        ACCEPTED,
        AWAIT,
        VISITED,
    }

    Patient(String name, PatientIllnessType illnessType) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.illnessType = illnessType;
        this.status = PatientStatusTypes.DEFAULT;
    }

    public void setStatus(PatientStatusTypes status) {
        this.status = status;
    }

    public void setIllnessType(PatientIllnessType illnessType) {
        this.illnessType = illnessType;
    }

    public void setAllocatedDoctorType(Doctor.DoctorTypes allocatedDoctorType) {
        this.allocatedDoctorType = allocatedDoctorType;
    }

    public Doctor.DoctorTypes getAllocatedDoctorType() {
        return allocatedDoctorType;
    }

    public boolean payBill(Float paymentAmount) {
        String paymentAmountString = paymentAmount.toString();
        System.out.println(String.format("payed $%s", paymentAmountString));
        return true;
    }

    public PatientIllnessType getIllnessType() {
        return illnessType;
    }

    public UUID getId() {
        return id;
    }
}
