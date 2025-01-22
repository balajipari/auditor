package com.augx.auditor.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class UserDto {

    private UUID id;
    private String name;
    private String email;
    private String country;
    private Long contactNumber;
}
