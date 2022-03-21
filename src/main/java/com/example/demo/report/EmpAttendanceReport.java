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
@XmlRootElement(name = "empAttendanceReport")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmpAttendanceReport {
	@XmlElementWrapper(name = "empAttendances")
	@XmlElement(name = "empAttendance")
	List<EmpAttendanceR> empAttendance = new ArrayList<>();
}
