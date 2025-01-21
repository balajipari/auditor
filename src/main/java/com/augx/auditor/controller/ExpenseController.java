package com.augx.auditor.controller;

import com.augx.auditor.model.dto.ExpenseDto;
import com.augx.auditor.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public void createOrUpdateExpense(@RequestBody ExpenseDto expenseDto) {
        expenseService.createOrUpdateExpense(expenseDto);
    }

    @GetMapping
    public void getAllExpense() {
        expenseService.getAllExpense();
    }

    @GetMapping("/{id}")
    public void getExpenseById(@PathVariable(value = "id") UUID id) {
        expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCatgeory(@PathVariable("id") UUID id) {
        expenseService.deleteExpense(id);
    }
}
