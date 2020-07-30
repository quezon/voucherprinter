package com.ex.controller.print;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.service.printing.FileMergingService;
import com.ex.service.printing.PrintingService;


@RestController
@RequestMapping("/print")
public class PrintingController {
	@Autowired
	PrintingService printingService;
	
	@Autowired
	FileMergingService fileMergingService;
	
	@PostMapping("/doc")
	public boolean printDocument(@RequestBody String fileName) {
		List<String> fileNames = Arrays.asList(fileName);
		String mergedFile = fileMergingService.pdfMerge(fileNames);
		try {
			if (!printingService.run(mergedFile)) {
				throw new NullPointerException();
			}
		} catch (NullPointerException ex) {
			return false;
		}
		return true;
	}
	
	@PostMapping("/docs")
	public boolean printDocuments(@RequestBody List<String> fileNames) {
		String mergedFile = fileMergingService.pdfMerge(fileNames);
		try {
			if (!printingService.run(mergedFile)) {
				throw new NullPointerException();
			}
		} catch (NullPointerException ex) {
			return false;
		}
		return true;
	}
}
