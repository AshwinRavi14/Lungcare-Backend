package com.lungcare.backend.Service;

import com.lungcare.backend.Entity.Patient;
import com.lungcare.backend.Repository.PatientRepository;
import com.lungcare.backend.DTO.PatientRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository)
    {

        this.patientRepository= patientRepository;
    }
    public Patient createPatient (PatientRequestDTO dto)
    {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setContactNumber(dto.getContact());
        patient.setDiagnosisStatus("Pending");

        return patientRepository.save(patient);
    }

    public Patient savePatient(Patient patient)
    {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients()
    {
        return patientRepository.findAll();
    }

    public List<Patient> searchByName(String name)
    {
        return  patientRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Patient> filterByStatus (String Status)
    {
        return patientRepository.findByDiagnosisStatus(Status);
    }

    public List<Patient> filterByAge (int age)
    {
        return patientRepository.findByAgeGreaterThan(age);
    }

}
