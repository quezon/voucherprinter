package com.ex.service.cheque;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.fop.svg.PDFTranscoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import com.ex.entity.cheque.Cheque;
import com.ex.util.AmountTransformer;
import com.ibm.icu.text.DateFormat;

@Service
public class ChequeService {
	@Autowired
	Transformer transformer;

	@Autowired
	AmountTransformer amountTransformer;

	@Autowired
	SAXSVGDocumentFactory svgFactory;

	@Autowired
	private Environment environment;

	@Autowired
	DateFormat dfUS;

	@Value(value = "${cheque.file.input.svg}")
	String origFile;

	@Value(value = "${cheque.file.output.pdf}")
	String destFile;

	String ac, cheque_date, amt_words, amt_words2, payee, amt_number;
	String a, b, c, d, e, f, g, h;

	SVGDocument doc;
	
	public boolean create(Cheque cheque) throws TransformerException, IOException, TranscoderException {
		// if cheque passes validation
			performInOrder(cheque);
			return true;
	}
	
	public boolean create(List<Cheque> cheques) throws TransformerException, IOException, TranscoderException {
		for (Cheque cheque: cheques) {
			create(cheque);
		}
		return true;
	}

	public void getProperties(String bank) throws IOException {

		if (environment.getProperty("cheque." + bank + ".a") != null) {
			a = environment.getProperty("cheque." + bank + ".a");
			b = environment.getProperty("cheque." + bank + ".b");
			c = environment.getProperty("cheque." + bank + ".c");
			d = environment.getProperty("cheque." + bank + ".d");
			e = environment.getProperty("cheque." + bank + ".e");
			f = environment.getProperty("cheque." + bank + ".f");
			g = environment.getProperty("cheque." + bank + ".g");
			h = environment.getProperty("cheque." + bank + ".h");
		}

		ac = environment.getProperty("cheque." + bank + ".ac");
		cheque_date = environment.getProperty("cheque." + bank + ".cheque_date");
		amt_words = environment.getProperty("cheque." + bank + ".amt_words");
		amt_words2 = environment.getProperty("cheque." + bank + ".amt_words2");
		payee = environment.getProperty("cheque." + bank + ".payee");
		amt_number = environment.getProperty("cheque." + bank + ".amt_number");

		doc = (SVGDocument) svgFactory.createDocument(origFile + bank + ".svg");
	}

	public void changeDocumentElements(String payeeName, LocalDate localDate, double amount) {

		Element acElem = doc.getElementById(ac); // ac payee
		Element payeeElem = doc.getElementById(payee); // payee
		Element cheque_dateElem = doc.getElementById(cheque_date); // cheque_date
		Element amt_numberElem = doc.getElementById(amt_number); // amt_num
		Element amt_wordsElem = doc.getElementById(amt_words); // amt_word1
		Element amt_words2Elem = doc.getElementById(amt_words2); // amt_word2

		List<String> amtInWords = amountTransformer.divideAmountInWordsByHalf(amount);
		String cheque_date = dfUS.format(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		acElem.setTextContent("A/C Payee Only");
		payeeElem.setTextContent(payeeName);
		cheque_dateElem.setTextContent(cheque_date);
		amt_numberElem.setTextContent(amountTransformer.amountInNumberFormatted(amount));
		amt_wordsElem.setTextContent(amtInWords.get(0));
		amt_words2Elem.setTextContent(amtInWords.get(1));
	}

	public void outputSVGFile(Long id) throws TransformerException {
		Result result = new StreamResult(destFile + "cheque_" + id + ".svg");
		Source input = new DOMSource(doc);
		transformer.transform(input, result);
	}

	public void outputPDFFile(Long id) throws TranscoderException, IOException {
		TranscoderInput input_svg_image = new TranscoderInput(doc);

		OutputStream pdf_ostream = new FileOutputStream(destFile + "cheque_" + id + ".pdf");
		TranscoderOutput output_pdf_file = new TranscoderOutput(pdf_ostream);
		Transcoder transcoder = new PDFTranscoder();

		// Step-4: Write output to PDF format
		transcoder.transcode(input_svg_image, output_pdf_file);
		// Step 5- close / flush Output Stream
		pdf_ostream.flush();
		pdf_ostream.close();

	}

	public void performInOrder(Cheque cheque) throws TransformerException, IOException, TranscoderException {
		getProperties(cheque.getBankName());
		changeDocumentElements(cheque.getPayeeName(), cheque.getChequeDate(), cheque.getAmount());
		outputPDFFile(cheque.getId());
	}
}
