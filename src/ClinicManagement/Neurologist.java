package ClinicManagement;

import java.io.Serializable;
import java.util.UUID;

public class Neurologist extends Doctor implements Serializable {
    Neurologist(UUID id, String name, Admin admin) {
        super(id, name, admin);
        this.type = DoctorTypes.NEUROLOGIST;
    }
}
