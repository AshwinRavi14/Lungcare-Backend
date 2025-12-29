package com.lungcare.backend.Controller;

import com.lungcare.backend.DTO.ApiResponse;
import com.lungcare.backend.DTO.PatientRequestDTO;
import com.lungcare.backend.Entity.Patient;
import com.lungcare.backend.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @PostMapping
    public ApiResponse<Patient> addPatient(@Valid @RequestBody PatientRequestDTO dto) {
        Patient patient = patientService.createPatient(dto);
        return new ApiResponse<>(true, "Patient created successfully", patient);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/search")
    public List<Patient> searchPatients(@RequestParam String name) {
        return patientService.searchByName(name);
    }

    @GetMapping("/status")
    public List<Patient> getByStatus(@RequestParam String status) {
        return patientService.filterByStatus(status);
    }

    @GetMapping("/age")
    public List<Patient> getByAge(@RequestParam int age) {
        return patientService.filterByAge(age);
    }
}
