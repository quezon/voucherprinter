package com.ex.eliminate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ex.entity.Voucher;
import com.ex.entity.cheque.Cheque;


@Service
public class ExternalAPIService {
	RestTemplate restTemplate;

	@Value("${spring.external.url.doc.print}")
	String printURL;

	@Value("${spring.external.url.cheque.save}")
	String chequeURL;

	@Value("${spring.external.url.cheque.save.print}")
	String chequeSavePrintURL;

	@Value("${spring.external.url.doc.save}")
	String voucherFileURL;

	public boolean print(List<String> fileNames) {
		return restTemplate.postForObject(printURL, fileNames, Boolean.class);
	}

	public boolean print(String fileName) {
		List<String> fileNames = new ArrayList<String>();
		fileNames.add(fileName);
		return restTemplate.postForObject(printURL, fileNames, Boolean.class);
	}

	public boolean saveChequeFiles(List<Cheque> cheques) {
		return restTemplate.postForObject(chequeURL, cheques, Boolean.class);
	}

	public <T extends Voucher> boolean saveVoucherFile(T t) {
		return restTemplate.postForObject(voucherFileURL, t, Boolean.class);
	}

	public boolean savePrintChequeFile(List<Cheque> cheques) {
		return restTemplate.postForObject(chequeSavePrintURL, cheques, Boolean.class);
	}

}
