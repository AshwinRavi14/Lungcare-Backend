package com.lungcare.backend.Repository;

import com.lungcare.backend.Entity.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long>{

    List<Patient> findByNameContainingIgnoreCase(String name);

    List<Patient> findByDiagnosisStatus (String stauts);

    List<Patient> findByAgeGreaterThan (int age);
}
