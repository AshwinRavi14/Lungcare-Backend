package com.lungcare.backend.DTO;

import  lombok.Builder;
import  lombok.Data;

@Data
@Builder

public class PatientResponseDTO {

    private long id;
    private String name;
    private int age;
    private String status;
    private String doctorUsername;
}
