package com.ex.util;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.text.RuleBasedNumberFormat;

@Service
public class AmountTransformer {
	@Autowired
	RuleBasedNumberFormat enFormatter;
	
	public String amountInNumberFormatted(double amount) {
		return NumberFormatter
				.withLocale(Locale.US)
				.format(amount).toString();
	}
	
	public List<String> divideAmountInWordsByHalf(double amount) {
		String amountInWords = enFormatter.format(amount).toUpperCase() ;
		
		int indexOf11thSpace = 0;
		if (amountInWords.length() > 87) {
			//
			for (int i = 0; i < 11; i++) {
				indexOf11thSpace = amountInWords.indexOf(" ", indexOf11thSpace + 1);
			}
			
			return Arrays.asList(
				"***" + amountInWords.substring(0, indexOf11thSpace),	
				amountInWords.substring(indexOf11thSpace, amountInWords.length() - 1) + "***"	
			);
					 
		}
		return Arrays.asList( "***" + amountInWords + "***", "");
	}
}
