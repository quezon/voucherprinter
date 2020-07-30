package com.ex.controller.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.entity.Voucherable;
import com.ex.service.JSONService;
import com.ex.service.XDocService;

@RestController
@RequestMapping("/doc")
public class DocumentController {

	@Autowired
	JSONService jsonService;
	
	@Autowired
	XDocService xDoc;

	@PostMapping(value = "/save/xdoc")
	//HttpEntity<String> docBody
	public boolean createPDFWithXDoc(Voucherable voucher) throws Exception {
		String jsonString = jsonService.convertVoucherToJSONStringForXDoc(voucher);
		
		xDoc.createDocument(jsonString);
		return true;
	}
}
