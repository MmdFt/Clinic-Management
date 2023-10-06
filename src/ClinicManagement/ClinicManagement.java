package ClinicManagement;

import ClinicManagement.Doctor.DoctorTypes;

import java.io.*;

public class ClinicManagement{
    public static void main(String[] args) throws IOException {
        File file = new File("db.hamed");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream in = new ObjectOutputStream(fileOutputStream);

        Admin admin = new Admin();
        Cashier cashier1 = admin.addCashier("myCashier");
        Receptionist recp1 = admin.addReceptionist("myRececptionist");
        Doctor generalDoctor = admin.addDoctor("genDoc", DoctorTypes.GENERAL);
        Doctor cancerDoctor = admin.addDoctor("cancerDoc", DoctorTypes.CANCER_SPECIALIST);
        Doctor neuroDoctor = admin.addDoctor("neuroDoc", DoctorTypes.NEUROLOGIST);
        
        Patient pat01 = new Patient("pat01", PatientIllnessType.COLD);
        Patient pat02 = new Patient("pat02", PatientIllnessType.COLD);
        Patient pat03 = new Patient("pat03", PatientIllnessType.COLD);
        
        Patient pat11 = new Patient("pat11", PatientIllnessType.CANCER);
        Patient pat12 = new Patient("pat12", PatientIllnessType.CANCER);
        
        Patient pat21 = new Patient("pat21", PatientIllnessType.HEADACHE);
        Patient pat22 = new Patient("pat22", PatientIllnessType.HEADACHE);
        Patient pat23 = new Patient("pat23", PatientIllnessType.HEADACHE);
        
        admin.startPatientTreatmentFlow(pat01);
        admin.startPatientTreatmentFlow(pat02);
        admin.startPatientTreatmentFlow(pat03);
        
        admin.startPatientTreatmentFlow(pat11);
        admin.startPatientTreatmentFlow(pat12);
        
        admin.startPatientTreatmentFlow(pat21);
        admin.startPatientTreatmentFlow(pat22);
        admin.startPatientTreatmentFlow(pat23);

        admin.logAllEmployees();
        admin.logAllPatients();
        System.out.println("before general visits");
        System.out.println("------------------------------");
        admin.logInQueuePatients();
        generalDoctor.visit();
        generalDoctor.visit();
        
        System.out.println("after general visits");
        System.out.println("------------------------------");
        admin.logInQueuePatients();
        
        System.out.println("log information of one of the doctors:");
        admin.logDoctorInfo(neuroDoctor.getId());
        System.out.println("log information of one of the patients:");
        admin.logPatientInfo(pat11.getId());
        
        admin.payDoctorSalary(generalDoctor);
        admin.payDoctorSalary(neuroDoctor);
        in.writeObject(admin);

    }
}
