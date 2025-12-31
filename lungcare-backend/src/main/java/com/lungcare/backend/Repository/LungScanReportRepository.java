package com.lungcare.backend.Repository;

import com.lungcare.backend.Entity.LungScanReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LungScanReportRepository extends JpaRepository<LungScanReport,Long> {
    List<LungScanReport> findByPatientId(Long PatientId);
}
