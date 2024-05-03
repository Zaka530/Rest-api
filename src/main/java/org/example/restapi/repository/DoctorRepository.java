package org.example.restapi.repository;

import org.example.restapi.Entity.DoctorEntity;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<DoctorEntity,Long > {
    DoctorEntity findByName(String Name);
}
