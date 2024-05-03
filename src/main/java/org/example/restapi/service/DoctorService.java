package org.example.restapi.service;

import org.example.restapi.Entity.DoctorEntity;
import org.example.restapi.exception.DoctorAlreadyExist;
import org.example.restapi.exception.DoctorNotFoundException;
import org.example.restapi.model.Doctor;
import org.example.restapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorEntity registration(DoctorEntity doctorEntity) throws DoctorAlreadyExist {
        // Проверка на существование пользователя с таким именем
        DoctorEntity existingDoctor = doctorRepository.findByName(doctorEntity.getName());
        if (existingDoctor != null) {
            throw new DoctorAlreadyExist("Пользователь с таким именем уже существует!");
        }

        // Сохранение нового пользователя
        return doctorRepository.save(doctorEntity);
    }
    public Doctor getOneDoctor(Long id) throws DoctorNotFoundException {
        DoctorEntity doctor=doctorRepository.findById(id).get();
        if(doctor==null){
            throw new DoctorNotFoundException("Пользователь не был найден !");
        }

        return Doctor.toModel(doctor);
    }
    public Long delete(Long id) throws DoctorNotFoundException {
        doctorRepository.deleteById(id);
        return id;
    }
    public DoctorEntity update(Long id, DoctorEntity updatedDoctor) throws DoctorNotFoundException {
        // Находим существующего доктора по ID или выбрасываем исключение, если не найден
        DoctorEntity existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Доктор не найден!"));

        // Обновляем данные
        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setSpeciality(updatedDoctor.getSpeciality());
        existingDoctor.setExperience(updatedDoctor.getExperience());

        // Сохраняем обновленного доктора в базе данных и возвращаем его
        return doctorRepository.save(existingDoctor);
    }

}

