package com.ex.service.printing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

@Service
public class FileMergingService {
	
	AtomicInteger atomicInteger = new AtomicInteger(0); 
	
	public String createMergedFileName(String fileName) {
		Path path = Paths.get(fileName); 
        Path folder = path.getParent();
        int docNum = atomicInteger.addAndGet(1);
        return folder.toString() + docNum + "merged.pdf";
	}
	
	public String pdfMerge(List<String> fileNames) {
		PDFMergerUtility PDFmerger = new PDFMergerUtility();
		String mergedPDFFile = createMergedFileName(fileNames.get(0)); 
		PDFmerger.setDestinationFileName( mergedPDFFile );
		ArrayList<PDDocument> docs = new ArrayList<PDDocument>();
		
		for(String fileName: fileNames) {
			File file = new File(fileName);
			try {
				docs.add( PDDocument.load(file) );
				PDFmerger.addSource(file);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		try {
			PDFmerger.mergeDocuments();
			for(PDDocument doc: docs) {
				doc.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				Files.deleteIfExists(Paths.get(mergedPDFFile));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return mergedPDFFile;
	}
}
