package com.ex.converter.voucher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ex.entity.Voucher;
import com.ex.entity.VoucherEnum;

@Component
public class VoucherToFileNameConverter implements Converter<Voucher, String> {
 
	RestTemplate restTemplate;
	
	@Value("${spring.directory.voucher}")
	String saveVoucherTo;
	
	@Value("${spring.external.url.doc.name}")
	String urlToGetVoucherFileName;
	
    @Override
    public String convert(Voucher from) {
    	Map<String, String> voucherMap = new TreeMap<String, String>();
    	voucherMap.put("parentFolder", saveVoucherTo);
    	voucherMap.put("endsWith", VoucherEnum.getFileNameByClazz(from.getClass()));
    	voucherMap.put("number", Long.toString(from.getId()));
    	
    	return restTemplate.postForObject(urlToGetVoucherFileName, voucherMap, String.class);
    }
    
    public List<String> convert(List<Voucher> vouchers) {
    	List<String> strings = new ArrayList<String>();
    	for (Voucher voucher: vouchers) {
    		strings.add( convert(voucher) );
    	}
    	
    	return strings;
    }
}
