package com.example.demo.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
@Data
@XmlRootElement(name = "empAttendance")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmpAttendanceR {
	
	private String month;
	private Date date;
	private String day;
	private String id;
	private String employeeName;
	private String department;
	private Date firstInTime;
	private Date lastOutTime;
	private Double hoursOfWork;
	
}
