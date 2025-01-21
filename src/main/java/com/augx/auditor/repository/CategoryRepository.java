package com.augx.auditor.repository;

import com.augx.auditor.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    public List<Category> findAllByName(String name);
}
