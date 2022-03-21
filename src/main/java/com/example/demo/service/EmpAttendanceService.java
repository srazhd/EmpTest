package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EmpAttendance;

public interface EmpAttendanceService {
	EmpAttendance saveEmpAttendance(EmpAttendance ea);
	List<EmpAttendance> getAllEmpAttendance();
	List<EmpAttendance> getAllEmpAttendanceById(String Id);
}
