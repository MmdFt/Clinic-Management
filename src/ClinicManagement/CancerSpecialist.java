package ClinicManagement;

import java.io.Serializable;
import java.util.UUID;

public class CancerSpecialist extends Doctor implements Serializable {
    CancerSpecialist(UUID id, String name, Admin admin) {
        super(id, name, admin);
        this.type = DoctorTypes.CANCER_SPECIALIST;
    }
}
