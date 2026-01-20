package com.lungcare.backend.Controller;


import com.lungcare.backend.DTO.PatientRequestDTO;
import com.lungcare.backend.Entity.Patient;
import com.lungcare.backend.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@PreAuthorize("hasRole('DOCTOR')")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient addPatient(@Valid @RequestBody PatientRequestDTO dto,
                              Authentication auth) {
        return patientService.createPatient(dto, auth.getName());
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }


    @GetMapping("/search")
    public List<Patient> searchPatients(@RequestParam String name , Authentication auth) {
        return patientService.searchByName(name, auth.getName());
    }

    @GetMapping("/status")
    public List<Patient> getByStatus(@RequestParam String status, Authentication auth) {
        return patientService.filterByStatus(status, auth.getName());
    }

    @GetMapping("/age")
    public List<Patient> getByAge(@RequestParam int age, Authentication auth) {
        return patientService.filterByAge(age, auth.getName());
    }

    @GetMapping("/paged")
    public Page<Patient> getPatientsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            Authentication auth
    ) {
        return patientService.getPatientsPaged(page, size, sortBy, auth.getName());
    }

    @GetMapping("/my")
    public  Page<Patient> getMyPatients(Authentication authentication, Pageable pageable)
    {
        return  patientService.getPatientsForDoctor(authentication.getName(),pageable);
    }
}

