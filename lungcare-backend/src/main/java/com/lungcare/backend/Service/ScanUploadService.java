package com.lungcare.backend.Service;

import com.lungcare.backend.Entity.LungScanReport;
import com.lungcare.backend.Entity.Patient;
import com.lungcare.backend.Enum.ScanType;
import com.lungcare.backend.Repository.LungScanReportRepository;
import com.lungcare.backend.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.UUID;

@Service
public class ScanUploadService {

    private final LungScanReportRepository lungScanReportRepository;
    private final PatientRepository patientRepository;


    @Value("${file.upload.base-path:C:/lungcare/uploads}")
    private String baseUploadPath;

    public ScanUploadService(LungScanReportRepository lungScanReportRepository,
                             PatientRepository patientRepository) {
        this.lungScanReportRepository = lungScanReportRepository;
        this.patientRepository = patientRepository;
    }

    public LungScanReport uploadScan(Long patientId,
                                     MultipartFile file,
                                     ScanType scanType) {


        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is required");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found: " + patientId));

        validateFile(file);

        Path uploadDir = Paths.get(baseUploadPath, "lung-scans");

        try {
            Files.createDirectories(uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }

        String storedFileName =
                UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path filePath = uploadDir.resolve(storedFileName);

        try {
            file.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save scan file", e);
        }


        LungScanReport scanReport = new LungScanReport();
        scanReport.setPatient(patient);
        scanReport.setFileName(file.getOriginalFilename());
        scanReport.setFilePath(filePath.toString());
        scanReport.setContentType(file.getContentType());
        scanReport.setScanType(scanType);

        return lungScanReportRepository.save(scanReport);
    }

    private void validateFile(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType == null ||
                !(contentType.startsWith("image/") ||
                        contentType.equals("application/dicom") ||
                        contentType.equals("application/octet-stream"))) {

            throw new RuntimeException("Unsupported file format");
        }

        long maxSize = 10 * 1024 * 1024; // 10MB
        if (file.getSize() > maxSize) {
            throw new RuntimeException("File too large (max 10MB)");
        }
    }

    public List<LungScanReport> getScansByPatientId(Long patientId) {
        return lungScanReportRepository.findByPatientId(patientId);
    }

    public Resource loadscanfile(Long scanId)
    {
        LungScanReport  scan = lungScanReportRepository.findById(scanId)
                .orElseThrow(()-> new RuntimeException("scan not found"));

        try {
            Path path =  Paths.get(scan.getFilePath());
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists())
            {
                throw new RuntimeException("scan file not found in disk");
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid scan file path",e);
        }
    }
}
