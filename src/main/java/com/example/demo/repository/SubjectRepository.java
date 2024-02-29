package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SubjectEntity;

/**
 * 科目情報 Repository
 */
@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
}