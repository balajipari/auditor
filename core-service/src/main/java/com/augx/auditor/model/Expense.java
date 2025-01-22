package com.augx.auditor.model;

import com.augx.auditor.util.CommonConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table
public class Expense {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private String label;
    private Integer value;
    private Date date;
    private String currency;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private CommonConstants.ExpenseStatus status;

    @PrePersist
    private void setDefaultsBeforeSave() {
        this.date = null != date ? date : new Date();
        this.status = null != status ? status : CommonConstants.ExpenseStatus.PENDING;

    }
}
