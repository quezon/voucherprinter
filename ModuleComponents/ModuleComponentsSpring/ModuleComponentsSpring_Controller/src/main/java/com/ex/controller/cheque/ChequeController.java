package com.ex.controller.cheque;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ex.converter.cheque.ChequeToFileNameConverter;
import com.ex.entity.cheque.Cheque;
import com.ex.entity.cheque.ChequeOfVoucher;
import com.ex.service.cheque.ChequeDBService;
import com.ex.service.cheque.ChequeService;



@RestController
@RequestMapping("/cheque")
public class ChequeController {
	@Autowired
	ChequeService chequeFileWriterService;
	
	@Autowired
	ChequeDBService chequeDBService;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ChequeToFileNameConverter chequeToFileNameConverter;

	@PostMapping("/writeToPDF")
	public boolean writeChequeToPDF(@RequestBody Cheque cheque) throws TransformerException, IOException, TranscoderException {
		return chequeFileWriterService.create(cheque);
	}

	@PostMapping("/writeToPDFs")
	public boolean writeChequesToPDFs(@RequestBody List<Cheque> cheques)
			throws TransformerException, IOException, TranscoderException {
		return chequeFileWriterService.create(cheques);
	}

	@PostMapping("/save")
	public <T extends Cheque> void saveCheque(@RequestBody T cheque) throws TransformerException, IOException, TranscoderException {
		chequeDBService.saveCheque(cheque);
	}
	
	@PostMapping("/saves")
	public void saveCheques(@RequestBody List<Cheque> cheques) throws TransformerException, IOException, TranscoderException {
		chequeDBService.saveCheques(cheques);
	}
	
	@PostMapping("/saves/many")
	public void saveChequesOfVoucher(@RequestBody List<ChequeOfVoucher> cheques) throws TransformerException, IOException, TranscoderException {
		chequeDBService.saveChequesOfVoucher(cheques);
	}
}

//@PostMapping("/cheques/print") // format: print/cheques/cheque_2312.pdf,cheque_2313.pdf
//public boolean printCheques(@RequestBody List<String> fileNames) {
//	return restTemplate.postForObject("http://localhost:9019/print", fileNames, Boolean.class);
//}
//
//@PostMapping("/cheques/save/print")
//public boolean saveAndPrintCheques(@RequestBody List<Cheque> cheques)
//		throws TransformerException, IOException, TranscoderException {
//	boolean saved = chequeFileWriterService.create(cheques);
//	List<String> fileNames = chequeToFileNameConverter.convert(cheques);
//	if (saved) {
//		boolean printed = restTemplate.postForObject("http://localhost:9019/print/cheques", fileNames,
//				Boolean.class);
//		return printed;
//	}
//	return false;
//}