package com.augx.auditor.model.dto;

import com.augx.auditor.util.CommonConstants;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ExpenseDto {
        private UUID id;
        private String label;
        private Integer value;
        private Date date;
        private String currency;
        private UUID category;
}
