package com.blendin.nlp.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.blendin.nlp.ml.PredictIntent;
import com.blendin.nlp.ml.TokenizeText;
import com.blendin.nlp.ml.TrainModel;

@RestController
public class TestController {

	String line = "";

	@Autowired
	private TrainModel train;

	private PredictIntent predictIntent;

	private TokenizeText tokenizer;

	@GetMapping(path = "/predict/{inputText}")
	public void test3(@PathVariable("inputText") String inputText) throws IOException {
		predictIntent = new PredictIntent();
		tokenizer = new TokenizeText();
		predictIntent.predict(tokenizer.tokenize(inputText));
		System.out.println(predictIntent.getIntentName());
	}

	@GetMapping(path = "/train")
	public void test2() throws IOException {

		train.train();
	}

	@GetMapping(path = "/test")
	public void test() throws IOException {
		List<String> dataset = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/abhishek1.singhal/Downloads/dataset.csv"));
		while ((line = br.readLine()) != null) {
			String[] row = line.split(",");
			dataset.add(this.removeExtra(row[2]) + " " + this.removeExtra(row[1]));
		}
		br.close();
		this.writing(dataset);
	}

	public String removeExtra(String input) {
		return input.replace("\"", "");
	}

	public void writing(List<String> dataset) {
		try {
			OutputStream outputStream = new FileOutputStream("C:/Users/abhishek1.singhal/Downloads/output.train");
			Writer outputStreamWriter = new OutputStreamWriter(outputStream);

			for (String item : dataset) {
				outputStreamWriter.write(item + "\n");
			}

			outputStreamWriter.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
