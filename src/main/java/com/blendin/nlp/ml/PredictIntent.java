package com.blendin.nlp.ml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

/**
 * 
 * @author Abhishek1.Singhal
 *
 */

public class PredictIntent {

//	@Value("${path.model}")
	private String modelPath = "C:/Users/abhishek1.singhal/Downloads/model.bin";

	private int threshold = 60;

	private InputStream nlpModelFile;

	private DoccatModel model;

	private String[] tokens;

	private DocumentCategorizerME categorizer;

	private Map<String, Double> scoreMap;

	private String intentName;

	private int probability;

	private double[] scores;

	/**
	 * Starts the predicting chain
	 * 
	 * @param tokens
	 * @throws IOException
	 */
	public void predict(String[] tokens) throws IOException {
		this.setTokens(tokens);
		this.setCategorizer();
		this.setScores();
		this.setScoreMap();
		this.setBestProbability();
		this.setIntentName();
	}

	/**
	 * Getter method for intent name
	 * 
	 * @return String intentName
	 */
	public String getIntentName() {
		return intentName;
	}

	/**
	 * Getter method for probability
	 * 
	 * @return integer probability out of 100
	 */
	public int getProbability() {
		return probability;
	}

	/**
	 * It finds the best match of intent with respect to input text. Use it when
	 * getIntentId method returns 0
	 * 
	 * @return integer intentId
	 */
	public String findBestMatchIntentName() {
		return categorizer.getBestCategory(scores);
	}

	/**
	 * Getter method for scoreMap. Can be used in debugging.
	 * 
	 * @return Map scoreMap
	 */
	public Map<String, Double> getScoreMap() {
		return scoreMap;
	}

	/**
	 * This method sets the value of intent id which has probability greater than
	 * threshold otherwise it is set as 0 (zero)
	 */
	private void setIntentName() {
		if (probability >= threshold) {
			intentName = this.findBestMatchIntentName();
		}
	}

	/**
	 * This method finds the best probability from scores and set the probability
	 */
	private void setBestProbability() {
		probability = Arrays.stream(scores).mapToInt(i -> (int) (i * 100)).max().getAsInt();
	}

	/**
	 * Score Map setter. Score Map can be used to debug things to know probability
	 * of each intent
	 */
	private void setScoreMap() {
		scoreMap = categorizer.scoreMap(tokens);
	}

	/**
	 * Converts the double array to integer and multiply by 100 to get probability
	 * of success and sets the value of scores
	 */
	private void setScores() {
		scores = categorizer.categorize(tokens);
	}

	/**
	 * This method fetches the saved NLP model and set the value of categorizer
	 * 
	 * @throws IOException
	 */
	private void setCategorizer() throws IOException {
		nlpModelFile = new FileInputStream(modelPath);
		model = new DoccatModel(nlpModelFile);
		categorizer = new DocumentCategorizerME(model);
	}

	/**
	 * Setter method for tokens. Tokens are small broken down pieces of input text
	 * 
	 * @param tokens
	 */
	private void setTokens(String[] tokens) {
		this.tokens = tokens;
	}

}