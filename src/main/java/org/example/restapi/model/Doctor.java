package org.example.restapi.model;

import lombok.Getter;
import lombok.Setter;
import org.example.restapi.Entity.DoctorEntity;

@Getter
@Setter
public class Doctor {
    private Long id;
    private String bio;
    private int experience;
    private String name;
    private String speciality;

    public static  Doctor toModel(DoctorEntity entity){
        Doctor model=new Doctor();
        model.setId(entity.getId());
        model.setBio(entity.getBio());
        model.setExperience(entity.getExperience());
        model.setName(entity.getName());
        model.setSpeciality(entity.getSpeciality());
        return model;
    }




    public Doctor() {

    }
}
