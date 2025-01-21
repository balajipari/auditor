package com.augx.auditor.service.impl;

import com.augx.auditor.model.Category;
import com.augx.auditor.model.Expense;
import com.augx.auditor.model.dto.ExpenseDto;
import com.augx.auditor.repository.CategoryRepository;
import com.augx.auditor.repository.ExpenseRepository;
import com.augx.auditor.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    ModelMapper modelMapper = new ModelMapper();

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void createOrUpdateExpense(ExpenseDto expenseDto) {
        Expense expense = mapDtoToExpense(expenseDto);
        if (null == expense.getId() || expenseRepository.findById(expense.getId()).isPresent()) {
            expenseRepository.save(expense);
        }
    }

    public List<ExpenseDto> getAllExpense() {
        return modelMapper.map(expenseRepository.findAll(), new TypeToken<Expense>() {
        }.getType());
    }

    public ExpenseDto getExpenseById(UUID id) {
        Optional<Expense> optional = expenseRepository.findById(id);
        return optional.map(this::mapExpenseToDto).orElse(null);
    }

    public void deleteExpense(UUID id) {
        if (expenseRepository.findById(id).isPresent()) {
            expenseRepository.deleteById(id);
        }
    }

    public List<ExpenseDto> getExpenseByCategory(UUID categoryId) {
        return null;
    }

    private Expense mapDtoToExpense(ExpenseDto expenseDto) {
        Expense expense = null != expenseDto ? modelMapper.map(expenseDto, Expense.class) : null;
        if (null != expenseDto && null != expenseDto.getCategory()) {
            Optional<Category> categoryOptional = categoryRepository.findById(expenseDto.getCategory());
            categoryOptional.ifPresent(expense::setCategory);
        }
        return expense;
    }

    private ExpenseDto mapExpenseToDto(Expense expense) {
        return null != expense ? modelMapper.map(expense, ExpenseDto.class) : null;
    }
}
