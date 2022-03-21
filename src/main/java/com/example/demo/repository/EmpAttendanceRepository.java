package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EmpAttendance;
@Repository
public interface EmpAttendanceRepository extends JpaRepository<EmpAttendance, Long>{
	List<EmpAttendance> findByIdContainingIgnoreCase(String id);
}
