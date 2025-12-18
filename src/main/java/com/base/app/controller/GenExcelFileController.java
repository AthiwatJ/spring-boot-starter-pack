package com.base.app.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.app.service.ExcelFileGenarateService;
import com.base.app.service.ExeclFileModel;

@RestController
@RequestMapping("/excel")
public class GenExcelFileController {
	
	private ExcelFileGenarateService excelFileGenarateService;

	public GenExcelFileController(ExcelFileGenarateService excelFileGenarateService) {
		this.excelFileGenarateService = excelFileGenarateService;
	}
	
	
	@PostMapping
	public ResponseEntity<Object> original(@RequestBody List<ExeclFileModel> request) throws Exception {
		byte[] file = excelFileGenarateService.createFile(request);
		MediaType mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("inline"+"filename=\"" +"file.xlsx"+ "\"").build());
		headers.setCacheControl(CacheControl.noCache().cachePrivate().mustRevalidate());
		headers.setContentType(mediaType);
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(new ByteArrayInputStream(file)));
	}

}
