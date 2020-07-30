package com.ex.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.service.printing.FileNameService;
import com.ex.util.TypedArrayList;

import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.document.tools.json.JSONDataProvider;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

@Service
public class XDocService {
	@Autowired
	FileNameService fns;
	
	@Autowired
	ReflectionService reflectionService;
	
	public FieldsMetadata getFieldsMetadata(List<String> declaredFields) {
		FieldsMetadata metadata = new FieldsMetadata();
		for (String field: declaredFields) {
			metadata.addFieldAsList(field);
		}
		return metadata;
	}
	
	public String createSerialName(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		return fns.createSerialName(
				jsonObject.getString("parentFolder"), 
				jsonObject.getString("endsWith"), 
				jsonObject.getLong("fileNumber")
			);
	}
	
	public String getKeyValue(String jsonString, String key) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		return jsonObject.getString(key);
	}
	
	/*
	 * Create document based on JSON Data
	 */
	public void createDocument(String jsonString) throws Exception {
		InputStream template = new FileInputStream(new File(getKeyValue(jsonString, "template")));
		InputStream json = new ByteArrayInputStream(jsonString.getBytes());
		IXDocReport report = XDocReportRegistry.getRegistry().loadReport(template, TemplateEngineKind.Velocity);
		JSONDataProvider jdata = new JSONDataProvider(json, null);
		IContext context = report.createContext();
		
		jdata.populateContext(report, context);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		report.process(context, out);
		outputPDFFile(out, createSerialName(jsonString));
	}
	
	/*
	 * Create document based on Java Model Data
	 */
	private void createDocument(TypedArrayList<?> list, String inputTemplate, String outputFile) throws IOException, XDocReportException {
		InputStream in= new FileInputStream(new File(inputTemplate));
		IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);
		
		List<String> declaredFields = reflectionService.getDeclaredFields(list);
		FieldsMetadata metadata = this.getFieldsMetadata(declaredFields);
		IContext context; // must provide context builder for each Java Model
	}
	
	public static void outputPDFFile(ByteArrayOutputStream outin, String outputPDF) throws FileNotFoundException, XDocConverterException {
		Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);

		IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

		InputStream in= new ByteArrayInputStream(outin.toByteArray());
		OutputStream out = new FileOutputStream(new File(outputPDF));
		converter.convert(in, out, options);
	}
	
	/*
	 * prototype
	 */
//	public static void createDocument(String inputTemplate, String outputFile) throws IOException, XDocReportException {
//		//input "C:/Users/vorga/Documents/262/voucher_templates/CashVoucher.odt"
//		//output  "C:/Users/vorga/Documents/262/voucher_templates/CashVoucher.pdf"
//		InputStream in= new FileInputStream(new File(inputTemplate));
//		IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);
//
//		FieldsMetadata metadata = new FieldsMetadata();
//		metadata.addFieldAsList("particulars.ref");
//		metadata.addFieldAsList("particulars.doc");
//		metadata.addFieldAsList("particulars.description");
//		metadata.addFieldAsList("particulars.amount");
//		report.setFieldsMetadata(metadata);
//		
//		
//		List<Particular> particulars = new ArrayList<Particular>();
//		
//		particulars.add(new Particular("PIV754755","Purchase Invoice","Steel Bars",2000.00));
//		particulars.add(new Particular("PIV754756","Purchase Invoice","Sheet Metal",2000.00));
//		
//		
//		
//		// 2) Create Java model context 
//		IContext context = report.createContext();
//		context.put("number", "000002");
//		context.put("payee", "Union Steel");
//		context.put("date", LocalDate.of(2020, 4, 2).toString());
//		context.put("amountWords", "PHP FOUR THOUSAND AND 00/100");
//		context.put("particulars", particulars);
//		
//		// 3) Generate report by merging Java model with the ODT
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		report.process(context, out);
//		outputPDFFile(out, outputFile);
//	}
}
