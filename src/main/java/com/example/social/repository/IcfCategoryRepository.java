package com.example.social.repository;

import com.example.social.model.IcfCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IcfCategoryRepository extends JpaRepository<IcfCategory, Long> {
}