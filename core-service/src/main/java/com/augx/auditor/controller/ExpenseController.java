package com.augx.auditor.controller;

import com.augx.auditor.model.dto.ExpenseDto;
import com.augx.auditor.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseDto createOrUpdateExpense(@RequestBody ExpenseDto expenseDto) {
        return expenseService.createOrUpdateExpense(expenseDto);
    }

    @GetMapping
    public List<ExpenseDto> getAllExpense() {
        return expenseService.getAllExpense();
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpenseById(@PathVariable(value = "id") UUID id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCatgeory(@PathVariable("id") UUID id) {
        expenseService.deleteExpense(id);
    }
}
