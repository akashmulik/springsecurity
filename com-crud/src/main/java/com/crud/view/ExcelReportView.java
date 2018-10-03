package com.crud.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.crud.entities.UsersBean;

public class ExcelReportView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Sheet excelSheet = workbook.createSheet("Users Data");

		List<UsersBean> usersList = (List) model.get("usersList");

		Row header = excelSheet.createRow(0);
		header.createCell(0).setCellValue("User ID");
		header.createCell(1).setCellValue("Name");
		header.createCell(2).setCellValue("Email");
		header.createCell(3).setCellValue("Mobile");
		header.createCell(4).setCellValue("City");

		int rowNum = 1;
		for (UsersBean user : usersList) {
			Row row = excelSheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getName());
			row.createCell(2).setCellValue(user.getEmail());
			row.createCell(3).setCellValue(user.getMobile());
			row.createCell(4).setCellValue(user.getCity());
		}
	}
}