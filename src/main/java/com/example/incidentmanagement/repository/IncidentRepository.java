package com.example.incidentmanagement.repository;

import com.example.incidentmanagement.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    // 基础的CRUD操作由JpaRepository提供
    // 可以根据需要添加自定义查询方法
} 