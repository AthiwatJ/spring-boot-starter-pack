package com.base.app.service;

import java.util.List;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class ExeclFileModel {
	
	private String sheetName;
	private List<CellData> headers;
	private List<List<CellData>> items;
	
	public ExeclFileModel() {}
	
	private ExeclFileModel(Builder builder) {
		this.sheetName = builder.sheetName;
		this.headers = builder.headers;
		this.items = builder.items;
	}

	public String getSheetName() {
		return sheetName;
	}

	public List<CellData> getHeaders() {
		return headers;
	}

	public List<List<CellData>> getItems() {
		return items;
	}



	public static class Builder {
		private String sheetName;
		private List<CellData> headers;
		private List<List<CellData>> items;
		
		public Builder sheetName(String sheetName) {
			 this.sheetName = sheetName;
			 return this;
		 }
		
		 public Builder headers(List<CellData> headers) {
			 this.headers = headers;
			 return this;
		 }
		 
		 public Builder data(List<List<CellData>> items) {
			 this.items = items;
			 return this;
		 }
		 
		 public ExeclFileModel build() {
			 return new ExeclFileModel(this);
		 }
	}
	
	public static class CellData {
		private CellDataStyle style;
		private String data;
		
		public CellDataStyle getStyle() {
			return style;
		}
		public void setStyle(CellDataStyle style) {
			this.style = style;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	}
	
	public static class CellDataStyle {
		private HorizontalAlignment alignment;
		
		public HorizontalAlignment getAlignment() {
			return alignment;
		}
		public void setAlignment(HorizontalAlignment alignment) {
			this.alignment = alignment;
		}
	}
	
}
