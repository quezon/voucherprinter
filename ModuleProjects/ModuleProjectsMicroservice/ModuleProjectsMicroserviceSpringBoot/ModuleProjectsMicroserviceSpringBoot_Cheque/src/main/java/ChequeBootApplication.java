

import java.util.Locale;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;

@SpringBootApplication
@EntityScan(basePackages = {"com.ex.entity.cheque"})
@ComponentScan(basePackages = {"com.ex.controller.cheque", "com.ex.service.cheque", "com.ex.util","com.ex.repo.cheque", "com.ex.converter.cheque"})
@EnableJpaRepositories(basePackages = {"com.ex.repo.cheque"})
public class ChequeBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChequeBootApplication.class, args);
	}
	
	@Bean("chequeUSRule")
	public String chequeUSRule() {
		return "%dollars-and-cents:\n" + "    x.0: << [and >%%cents>];\n" + "    0.x: >%%cents>;\n"
				+ "    0: zero dollars; one dollar; =%%main= dollars;\n" + "%%main:\n"
				+ "    zero; one; two; three; four; five; six; seven; eight; nine;\n"
				+ "    ten; eleven; twelve; thirteen; fourteen; fifteen; sixteen;\n"
				+ "        seventeen; eighteen; nineteen;\n" + "    20: twenty[->>];\n" + "    30: thirty[->>];\n"
				+ "    40: forty[->>];\n" + "    50: fifty[->>];\n" + "    60: sixty[->>];\n"
				+ "    70: seventy[->>];\n" + "    80: eighty[->>];\n" + "    90: ninety[->>];\n"
				+ "    100: << hundred[ >>];\n" + "    1000: << thousand[ >>];\n" + "    1,000,000: << million[ >>];\n"
				+ "    1,000,000,000: << billion[ >>];\n" + "    1,000,000,000,000: << trillion[ >>];\n"
				+ "    1,000,000,000,000,000: =#,##0=;\n" + "%%cents:\n" + "    100: <%%main< cent[s];\n"
				+ "%dollars-and-hundredths:\n" + "    x.0: <%%main< and >%%hundredths>/100;\n" // this used to end in
																								// 'dollars' but that
																								// should be added later
				+ "%%hundredths:\n" + "    100: <00<;\n";
	}

	@Bean()
	public String getXMLParserClassName() {
		return XMLResourceDescriptor.getXMLParserClassName();
	}

	@Bean()
	public SAXSVGDocumentFactory svgFactory() {
		return new SAXSVGDocumentFactory(getXMLParserClassName());
	}

	@Bean()
	public RuleBasedNumberFormat enFormatter() {
		return new RuleBasedNumberFormat(chequeUSRule(), Locale.US);
	}

	@Bean()
	public DateFormat dfUS() {
		return DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
	}

	@Bean()
	public TransformerFactory tFactory() {
		return TransformerFactory.newInstance();
	}

	@Bean()
	public Transformer transformer() throws TransformerException {
		return tFactory().newTransformer();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
