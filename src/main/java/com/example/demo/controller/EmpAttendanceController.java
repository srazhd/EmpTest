package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.helper.ExcelHelper;
import com.example.demo.model.EmpAttendance;
import com.example.demo.report.EmpAttendanceR;
import com.example.demo.report.EmpAttendanceReport;
import com.example.demo.service.EmpAttendanceService;
import com.example.demo.service.PrintingService;

@Controller
@RequestMapping("/empAttendsnce")
public class EmpAttendanceController {
	private final EmpAttendanceService empAttendanceService;
	private final PrintingService printingService;
	protected static final String ERROR = "Error is : {}, {}";

	public EmpAttendanceController(EmpAttendanceService empAttendanceService, PrintingService printingService) {
		super();
		this.empAttendanceService = empAttendanceService;
		this.printingService = printingService;
	}

	@GetMapping("/")
	public String home(Model model) {
		List<EmpAttendance> empAttendanceList = empAttendanceService.getAllEmpAttendance();
		if (empAttendanceList != null) {
			setInOut(empAttendanceList);
		}

		model.addAttribute("empAttendanceList", empAttendanceList);

		return "index";
	}

	@GetMapping("/saveData")
	public String saveDataInDB(Model model) {
		try {
			FileInputStream file = new FileInputStream(new File("D:\\empAttendance.xls"));
			List<EmpAttendance> empAttendances = ExcelHelper.excelToTutorials(file);
			
			empAttendances.forEach(empAttendance -> {
				empAttendanceService.saveEmpAttendance(empAttendance);
			});

		} catch (Exception e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
 
		List<EmpAttendance> empAttendanceList = empAttendanceService.getAllEmpAttendance();
		if (empAttendanceList != null) {
			setInOut(empAttendanceList);
		}

		model.addAttribute("empAttendanceList", empAttendanceList);

		return "index";
	}

	@GetMapping("/getAllById")
	public String getdatabyid(@RequestParam String id, Model model) {
		List<EmpAttendance> empAttendanceList = empAttendanceService.getAllEmpAttendanceById(id);
		if (empAttendanceList != null) {
			setInOut(empAttendanceList);
		}

		model.addAttribute("empAttendanceList", empAttendanceList);

		return "index :: empAttendance";
	}

	public void setInOut(List<EmpAttendance> empAttendanceList) {
		empAttendanceList.forEach(empAttendance -> {
			try {
				SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss a");
				empAttendance.getDate().setHours(9);
				empAttendance.getDate().setMinutes(30);
				Date inTime = new Date(empAttendance.getDate().getTime());

				empAttendance.getDate().setHours(18);
				empAttendance.getDate().setMinutes(30);
				Date outTime = new Date(empAttendance.getDate().getTime());

				Date firstInTime = empAttendance.getFirstInTime();
				Date lastOutTime = empAttendance.getLastOutTime();

				if (!(inTime.compareTo(firstInTime) > 0)) {
					empAttendance.setLateIn("Late");
				}

				if (!(outTime.compareTo(lastOutTime) < 0)) {
					empAttendance.setFastOut("Fast");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@GetMapping("/showReport")
	public ResponseEntity<byte[]> showReport(Model model, HttpServletRequest request) {
		String message;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html"));
		headers.add("X-Content-Type-Options", "nosniff");

		List<EmpAttendance> empAttendanceList = empAttendanceService.getAllEmpAttendance();

		EmpAttendanceReport report = new EmpAttendanceReport();
		empAttendanceList.forEach(ea -> {
			EmpAttendanceR ear = new EmpAttendanceR();
			ear.setMonth(ea.getMonth());
			ear.setDate(ea.getDate());
			ear.setDay(ea.getDay());
			ear.setId(ea.getId());
			ear.setEmployeeName(ea.getEmployeeName());
			ear.setDepartment(ea.getDepartment());
			ear.setFirstInTime(ea.getFirstInTime());
			ear.setLastOutTime(ea.getLastOutTime());
			ear.setHoursOfWork(ea.getHoursOfWork());

			report.getEmpAttendance().add(ear);

		});

		byte[] byt = getPDFByte(report, "empAttendancereport.xsl", request);

		headers.setContentType(new MediaType("application", "pdf"));
		return new ResponseEntity<>(byt, headers, HttpStatus.OK);
	}

	protected byte[] getPDFByte(Object report, String templateName, HttpServletRequest request) {
		byte[] byt = null;
		try {
			byt = printingService.getPDFReportByte(report, getOnScreenReportTemplate(templateName), request);
		} catch (Exception e) {
			return null;
		}
		return byt;
	}

	protected String getOnScreenReportTemplate(String templateName) {
		StringBuilder template = new StringBuilder();
		try {
			String[] tname = templateName.split("\\.");
			template = new StringBuilder(this.getClass().getClassLoader().getResource("static").toURI().getPath())
					.append(File.separator).append("xsl").append(File.separator)
					.append(tname[0] + "-" + "200000" + ".xsl");

		} catch (Exception e) {

		}

		File file = new File(template.toString());
		if (!file.exists()) {
			try {
				template = new StringBuilder(this.getClass().getClassLoader().getResource("static").toURI().getPath())
						.append(File.separator).append("xsl").append(File.separator).append(templateName);
			} catch (Exception e1) {

			}
		}
		return template.toString();
	}
}
