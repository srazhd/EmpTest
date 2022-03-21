package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
@Data
@Entity
public class EmpAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sl;
	private String month;
	private Date date;
	private String day;
	private String id;
	private String employeeName;
	private String department;
	private Date firstInTime;
	private Date lastOutTime;
	private Double hoursOfWork;
	
	@Transient
	private String lateIn;
	@Transient
	private String fastOut;
	

}
