package com.ex.service.printing;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PrintingService {

	@Value("${spring.printer1}")
	String printer;
	
	public boolean run(String fileName) {
		
		PDDocument document;
		try {
			document = PDDocument.load(new File(fileName));
			PrintService ps = findPrintService("Hewlett-Packard LaserJet 3055"); //printer
			PrinterJob job = PrinterJob.getPrinterJob();
			
			job.setPageable(new PDFPageable(document));
			job.setPrintService(ps);
	
			job.print();

			document.close();
		} catch (IOException | PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public PrintService findPrintService(String printerName) {
		for (PrintService service : PrinterJob.lookupPrintServices()) {
			if (service.getName().equalsIgnoreCase(printerName))
				return service;
		}

		return null;
	}

}
