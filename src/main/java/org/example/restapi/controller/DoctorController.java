package org.example.restapi.controller;

import org.example.restapi.exception.DoctorNotFoundException;
import org.example.restapi.service.DoctorService;
import org.example.restapi.Entity.DoctorEntity;
import org.example.restapi.exception.DoctorAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    //Добавление пользователя
    @PostMapping
    public ResponseEntity registration(@RequestBody DoctorEntity doctor) {
        try {
            doctorService.registration(doctor);
            return ResponseEntity.ok("Пользователь успешно сохранен! ");
        } catch (DoctorAlreadyExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    //ГЕТ запрос
    @GetMapping("/{id}")
    public ResponseEntity getOneDoctors(@PathVariable Long id) {
        try {
            // Здесь можно добавить логику для получения списка врачей из репозитория
            return ResponseEntity.ok(doctorService.getOneDoctor(id));
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка сервера ! ");
        }
    }

    //Обновление
    @PutMapping("/{id}")
    public ResponseEntity updateDoctor(@PathVariable Long id, @RequestBody DoctorEntity updatedDoctor) {
        try {
            DoctorEntity doctor = doctorService.update(id, updatedDoctor);
            return ResponseEntity.ok(doctor);
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка сервера !");
        }
    }

    //Удаление
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDoctor(@PathVariable Long id) throws DoctorNotFoundException {
        try {
            return ResponseEntity.ok(doctorService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка сервера ! ");
        }
    }
}



