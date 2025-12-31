package com.lungcare.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lungcare.backend.Enum.ScanType;
import com.lungcare.backend.Enum.DiagnosisStatus;  // ← ADD THIS!
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "lung_scan_reports")
@Data
public class LungScanReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;
    private String contentType;

    @Enumerated(EnumType.STRING)
    private ScanType scanType;

    @Enumerated(EnumType.STRING)
    private DiagnosisStatus diagnosisStatus = DiagnosisStatus.PENDING;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;
}
