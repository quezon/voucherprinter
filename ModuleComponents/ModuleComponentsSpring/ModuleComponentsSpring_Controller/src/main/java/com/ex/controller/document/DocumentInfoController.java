package com.ex.controller.document;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.service.printing.FileNameService;

@RestController
@RequestMapping("/doc")
public class DocumentInfoController {
	
	@Autowired
	FileNameService fns;

	@Value("#{T(java.lang.Integer).valueOf(${digit.size})")
	int digitSize;

	@GetMapping(value = "/get/name")
	public String getFileName(@RequestBody Map<String, String> map) {
		return fns.createSerialName(map.get("parentFolder"), map.get("endsWith"), Long.valueOf(map.get("number")));
	}

	@GetMapping(value = "/get/digit")
	public Integer getDigitSize() {
		return digitSize;
	}

	@GetMapping(value = "/get/max")
	public Long getMaxId() {
		return fns.maxNumber();
	}
}
