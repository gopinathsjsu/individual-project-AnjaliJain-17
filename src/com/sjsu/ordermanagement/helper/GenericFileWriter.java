package com.sjsu.ordermanagement.helper;

import java.util.List;

import com.sjsu.ordermanagement.model.ValidationResult;

public class GenericFileWriter {

	public IOutputFileWriter write(ValidationResult result) {
		if(!result.isValid()) {
			return new DefaultFileWriter();
		}else {
		  return new CsvFileWriter();
		}
		
	}
}
