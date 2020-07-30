package com.ex.service.printing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileNameService {
	@Autowired
	StringBuffer genZeroes;

	@Value("#{T(java.lang.Integer).valueOf(${digit.size})")
	int digitSize;

	public String createSerialName(String parentFolder, String endsWith, Long number) {
		StringBuffer sb = new StringBuffer(endsWith);

		sb.insert(endsWith.indexOf("."), createSequence(number).toString());

		return parentFolder + sb.toString();
	}

	public boolean numberWithinRange(Long number) {
		return number <= Math.pow(10, digitSize) - 1;
	}

	public long maxNumber() {
		return (long) (Math.pow(10, digitSize) - 1);
	}

	public StringBuffer createSequence(Long number) {
		if (!numberWithinRange(number)) {
			return null;
		}

		int digits = Long.toString(number).length();
		genZeroes.replace(digitSize - digits, digitSize, "");
		genZeroes.append(number);
		return genZeroes;
	}
}
