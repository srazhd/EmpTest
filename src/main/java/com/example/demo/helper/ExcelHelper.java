package com.example.demo.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.EmpAttendance;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Month", "Date", "Day", "ID", "Employee Name", "Department", "First-In Time",
			"Last-Out Time", "Hours of Work" };
	static String SHEET = "EmpAtten";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<EmpAttendance> excelToTutorials(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			List<EmpAttendance> empAttendances = new ArrayList<EmpAttendance>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				EmpAttendance empAttendance = new EmpAttendance();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0:
						empAttendance.setMonth(currentCell.getStringCellValue());
						break;
					case 1:
						empAttendance.setDate(currentCell.getDateCellValue());
						break;
					case 2:
						empAttendance.setDay(currentCell.getStringCellValue());
						break;
					case 3:
						empAttendance.setId(currentCell.getStringCellValue());
						break;
					case 4:
						empAttendance.setEmployeeName(currentCell.getStringCellValue());
						break;
					case 5:
						empAttendance.setDepartment(currentCell.getStringCellValue());
						break;
					case 6:
						empAttendance.setFirstInTime(currentCell.getDateCellValue());
						break;
					case 7:
						empAttendance.setLastOutTime(currentCell.getDateCellValue());
						break;
					case 8:
						empAttendance.setHoursOfWork(currentCell.getNumericCellValue());
						break;

					default:
						break;
					}
					cellIdx++;
				}
				empAttendances.add(empAttendance);
			}
			workbook.close();
			return empAttendances;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}