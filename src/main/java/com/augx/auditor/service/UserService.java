package com.augx.auditor.service;

import com.augx.auditor.model.dto.UserDto;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public void createOrUpdateUser(UserDto user);
    public List<UserDto> getUsers();
    public UserDto getUserById(UUID id);
    public void delete(UUID id);
}
