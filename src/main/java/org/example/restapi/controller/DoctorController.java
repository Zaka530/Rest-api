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

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody DoctorEntity doctor) {
        try {
            doctorService.registration(doctor);
            return ResponseEntity.accepted().body(doctor);
        } catch (DoctorAlreadyExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneDoctors(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(doctorService.getOneDoctor(id));
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody DoctorEntity updatedDoctor) {
        try {
            DoctorEntity doctor = doctorService.update(id, updatedDoctor);
            return ResponseEntity.accepted().body(doctor);
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        try {
            doctorService.delete(id);
            return ResponseEntity.accepted().build();
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error");
        }
    }
}



