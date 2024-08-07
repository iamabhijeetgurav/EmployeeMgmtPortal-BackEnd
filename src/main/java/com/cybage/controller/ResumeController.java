package com.cybage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cybage.entity.ResumeSummary;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ResumeController {

	@Value("${upload-dir}")
	private String uploadDir;

	private ResumeSummary resumeSummary = new ResumeSummary();

	@PostMapping("/upload")
	public ResponseEntity<ResumeSummary> uploadResume(@RequestParam("file") MultipartFile file) throws Exception {
		// Check if the file is empty
		if (file.isEmpty()) {
			resumeSummary.setErrorResponse("Please upload a file.");
			return ResponseEntity.badRequest().body(resumeSummary);
		}

		// Normalize the file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Copy file to the target location (upload directory)
			Path copyLocation = Paths.get(uploadDir + File.separator + fileName);
			Files.copy(file.getInputStream(), copyLocation);
			String summary = generateSummary(copyLocation.toString());
			resumeSummary.setResponse("File uploaded successfully: " + fileName);
			resumeSummary.setSummary(summary);
			return ResponseEntity.ok().body(resumeSummary);
		} catch (IOException e) {
			e.printStackTrace();
			resumeSummary.setErrorResponse("Failed to upload file.");
			return ResponseEntity.status(500).body(resumeSummary);
		}
	}

	private String generateSummary(String filePath) throws Exception {
		try {
			String text = "";

			if (filePath.endsWith(".docx")) {
				XWPFDocument doc = new XWPFDocument(new FileInputStream(new File(filePath)));
				XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
				text = extractor.getText();
			} else if (filePath.endsWith(".pdf")) {
				PDDocument doc = PDDocument.load(new File(filePath));
				PDFTextStripper stripper = new PDFTextStripper();
				text = stripper.getText(doc);
				doc.close();
			} else {
				FileInputStream inputStream = new FileInputStream(new File(filePath));
				text = IOUtils.toString(inputStream, "UTF-8");
			}

			Properties props = new Properties();
			props.setProperty("annotators",
					"tokenize, ssplit, pos, lemma, ner, parse, sentiment, coref, natlog, openie");
			StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

			Document doc = new Document(text);
			StringBuilder summary = new StringBuilder();

			for (Sentence sent : doc.sentences()) {
				summary.append(sent.text()).append("\n");
			}

			return summary.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed to generate summary.";
		}
	}

}
