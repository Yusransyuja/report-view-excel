package com.create.reporting.reportview;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class ReportExcel extends AbstractXlsxView {

	@Override
    protected void buildExcelDocument(Map model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=Reporting.xlsx");
        List tempList = (List) model.get("report");

        Sheet sheet = workbook.createSheet("Reporting");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("No");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("NIK");
        header.createCell(3).setCellValue("Department");
        header.createCell(4).setCellValue("Email");
        
        List reportList = (List) tempList.get(0);
        int rowNum = 1;
        
        for (int i = 0; i < reportList.size(); i++) {
        	Employee employee = (Employee) reportList.get(i);

            Row headers = sheet.createRow(rowNum++);
            headers.createCell(0).setCellValue(rowNum++);
            headers.createCell(1).setCellValue(employee.getName());
            headers.createCell(2).setCellValue(employee.getNik());
            headers.createCell(3).setCellValue(employee.getDept);
            headers.createCell(4).setCellValue(employee.getMail());

        }

    }
}
