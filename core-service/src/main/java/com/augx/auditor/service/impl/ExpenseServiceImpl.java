package com.augx.auditor.service.impl;

import com.augx.auditor.model.Category;
import com.augx.auditor.model.Expense;
import com.augx.auditor.model.User;
import com.augx.auditor.model.dto.ExpenseDto;
import com.augx.auditor.repository.CategoryRepository;
import com.augx.auditor.repository.ExpenseRepository;
import com.augx.auditor.repository.UserRepository;
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
    private final UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ExpenseDto createOrUpdateExpense(ExpenseDto expenseDto) {
        Expense expense = mapDtoToExpense(expenseDto);
        if (null == expense.getId() || expenseRepository.findById(expense.getId()).isPresent()) {
            return mapExpenseToDto(expenseRepository.save(expense));
        }
        return null;
    }

    public List<ExpenseDto> getAllExpense() {
        return modelMapper.map(expenseRepository.findAll(), new TypeToken<List<Expense>>() {
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
        if (null != expenseDto ) {
            if (null != expenseDto.getCategoryId()) {
                Optional<Category> categoryOptional = categoryRepository.findById(expenseDto.getCategoryId());
                categoryOptional.ifPresent(expense::setCategory);
            }
            if (null != expenseDto.getUserId()) {
                Optional<User> userOptional = userRepository.findById(expenseDto.getUserId());
                userOptional.ifPresent(expense::setUser);
            }
        }
        return expense;
    }

    private ExpenseDto mapExpenseToDto(Expense expense) {
        return null != expense ? modelMapper.map(expense, ExpenseDto.class) : null;
    }
}
