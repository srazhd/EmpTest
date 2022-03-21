package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.EmpAttendance;
import com.example.demo.repository.EmpAttendanceRepository;
import com.example.demo.service.EmpAttendanceService;
@Service
public class EmpAttendanceServiceImpl implements EmpAttendanceService{
	private final EmpAttendanceRepository empAttendanceRepository;
	
	
	public EmpAttendanceServiceImpl(EmpAttendanceRepository empAttendanceRepository) {
		super();
		this.empAttendanceRepository = empAttendanceRepository;
	}

	@Override
	public EmpAttendance saveEmpAttendance(EmpAttendance ea) {
		
		return empAttendanceRepository.save(ea);
	}

	@Override
	public List<EmpAttendance> getAllEmpAttendance() {
		
		return empAttendanceRepository.findAll();
	}

	@Override
	public List<EmpAttendance> getAllEmpAttendanceById(String id) {
		
		return empAttendanceRepository.findByIdContainingIgnoreCase(id);
	}

}
