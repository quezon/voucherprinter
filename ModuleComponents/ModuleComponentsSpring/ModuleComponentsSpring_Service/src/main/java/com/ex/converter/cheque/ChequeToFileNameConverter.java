package com.ex.converter.cheque;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.ex.entity.cheque.Cheque;

@Service
public class ChequeToFileNameConverter implements Converter<Cheque, String> {
 
    @Override
    public String convert(Cheque from) {
        return "cheque_" + from.getId() + ".pdf";
    }
    
    public List<String> convert(List<Cheque> cheques) {
    	List<String> strings = new ArrayList<String>();
    	for (Cheque cheque: cheques) {
    		strings.add( convert(cheque) );
    	}
    	
    	return strings;
    }
}
