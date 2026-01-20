package com.lungcare.backend.Service;

import com.lungcare.backend.Entity.Patient;
import com.lungcare.backend.Repository.PatientRepository;
import com.lungcare.backend.DTO.PatientRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public Page<Patient > getPatientsPaged(int page, int size, String sortBy, String doctorUsername)
    {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return patientRepository.findByDoctorUsername(doctorUsername,pageable);
    }

    public PatientService(PatientRepository patientRepository)
    {

        this.patientRepository= patientRepository;
    }
    public Patient createPatient (PatientRequestDTO dto, String name)
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

    public List<Patient> searchByName(String name, String doctorUsername)
    {
        return  patientRepository.findByNameContainingIgnoreCaseAndDoctorUsername(name,doctorUsername);
    }

    public List<Patient> filterByStatus (String Status,String doctorUsername)
    {
        return patientRepository.findByDiagnosisStatusAndDoctorUsername(Status,doctorUsername);
    }

    public List<Patient> filterByAge (int age,String doctorUsername)
    {
        return patientRepository.findByAgeGreaterThanAndDoctorUsername(age,doctorUsername);
    }

    public Page<Patient> getPatientsForDoctor (String username,Pageable pageable)
    {
        return patientRepository.findByDoctorUsername(username, pageable);
    }

}
