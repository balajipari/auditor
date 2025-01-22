package com.augx.auditor.service;

import com.augx.auditor.model.dto.ExpenseDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    public ExpenseDto createOrUpdateExpense(ExpenseDto expenseDto);

    public List<ExpenseDto> getAllExpense();

    public ExpenseDto getExpenseById(UUID id);

    public void deleteExpense(UUID id);

    public List<ExpenseDto> getExpenseByCategory(UUID categoryId);
}
