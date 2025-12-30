package com.lungcare.backend.Repository;

import com.lungcare.backend.Entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long>{

    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Patient> findByDiagnosisStatus (String status, Pageable pageable);

    List<Patient> findByNameContainingIgnoreCase(String name);

    List<Patient> findByDiagnosisStatus (String status);

    List<Patient> findByAgeGreaterThan (int age);
}
