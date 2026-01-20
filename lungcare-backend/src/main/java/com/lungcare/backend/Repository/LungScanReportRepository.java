package com.lungcare.backend.Repository;

import com.lungcare.backend.Entity.LungScanReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LungScanReportRepository extends JpaRepository<LungScanReport,Long> {
    List<LungScanReport> findByPatientId(Long PatientId);
    Optional<LungScanReport> findByIdAndPatientDoctorUsername(Long scanId, String username);
}
