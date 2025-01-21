package com.augx.auditor.model;

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

    public void setDate(Date date) {
        this.date = null != date ? date : new Date();
    }
}
