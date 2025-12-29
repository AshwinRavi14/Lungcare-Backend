package com.lungcare.backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/api/health")
    public String health()
    {
        return "LungCare Bckend is running Scuessfully";
    }
}
