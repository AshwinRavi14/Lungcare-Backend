package com.lungcare.backend.Controller;

import com.lungcare.backend.DTO.ApiResponse;
import com.lungcare.backend.Entity.LungScanReport;
import com.lungcare.backend.Enum.ScanType;
import com.lungcare.backend.Service.ScanUploadService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/scans")
public class ScanController {

    private ScanUploadService scanUploadService;

    public ScanController(ScanUploadService scanUploadService) {
        this.scanUploadService = scanUploadService;
    }

    @PostMapping(
            value = "/patients/{patientId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<LungScanReport>> uploadScan (
            @PathVariable Long patientId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("scanType") ScanType scanType
    ) {
        LungScanReport report =
                scanUploadService.uploadScan(patientId, file, scanType);

        return ResponseEntity.ok(
                ApiResponse.success("Scan uploaded successfully", report)
        );
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<ApiResponse<List<LungScanReport>>> getScansByPatient(
            @PathVariable Long patientId
    ) {
        List<LungScanReport> scans =
                scanUploadService.getScansByPatientId(patientId);

        return ResponseEntity.ok(
                ApiResponse.success("Scans retrieved successfully", scans)
        );
    }
    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/{scanId}/file")
    public ResponseEntity<Resource> getScanfile(@PathVariable Long scanId ,Authentication authentication)
    {
        Resource resource = scanUploadService.loadscanfile(scanId,authentication.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\"scan\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}
