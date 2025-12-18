package com.base.app.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.base.app.service.ExeclFileModel.CellData;
import com.base.app.service.ExeclFileModel.CellDataStyle;

@Service
public class ExcelFileGenarateService {

	public ExcelFileGenarateService() {
		// TODO Auto-generated constructor stub
	}
	
	public byte[] createFile(List<ExeclFileModel> model) throws Exception {
		Workbook workbook = createWorkbook(model);
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook.write(bos);
			return bos.toByteArray();
		}
	}
	
	private Workbook createWorkbook(List<ExeclFileModel> model) {
		Workbook workbook = new XSSFWorkbook();
		for(ExeclFileModel item: model) {
			Sheet sheet = workbook.createSheet(item.getSheetName());
			Row headerRow = sheet.createRow(0);
			List<CellData> header = item.getHeaders();
			for(int i = 0; i < header.size();i++) {
				Cell cell = headerRow.createCell(i);
				CellData headerData = header.get(i);
				cell.setCellValue(headerData.getData());
				serCellStyle(workbook, cell, headerData.getStyle());
			}
			
			for(int i = 0; i < item.getItems().size();i++) {
				Row row = sheet.createRow(i+1);
				List<CellData> data = item.getItems().get(i);
				
				for(int j = 0; j < data.size(); j++) {
					Cell cell = row.createCell(j);
					CellData itemData = data.get(j);
					cell.setCellValue(itemData.getData());
					serCellStyle(workbook, cell, itemData.getStyle());
				}
			}
		}
		return workbook;
	}
	
	private void serCellStyle(Workbook workbook,Cell cell, CellDataStyle reqStyle) {
		CellStyle cellStyle = workbook.createCellStyle();
		cell.setCellStyle(cellStyle);
		if(reqStyle.getAlignment() != null) {
			cellStyle.setAlignment(reqStyle.getAlignment());			
		}
	}
}
