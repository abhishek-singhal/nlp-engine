/**
 * 
 */
package com.blendin.nlp.ml;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.ml.EventTrainer;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 * @author Abhishek1.Singhal
 *
 */

@Component
public class TrainModel {

	@Value("${path.dataset}")
	private String datasetPath;

	@Value("${path.model}")
	private String modelPath;

	private InputStreamFactory inputStream;
	private TrainingParameters trainParams;
	private ObjectStream<DocumentSample> sampleStream;
	private DoccatModel model;

	public void train() throws IOException {
		this.setInputStream();

		this.setTrainingParams();

		this.setSampleStream();

		this.saveModel();
	}

	/**
	 * Save the model on disk for predicting
	 * 
	 * @throws IOException
	 */
	private void saveModel() throws IOException {
		model = DocumentCategorizerME.train("en", sampleStream, trainParams, new DoccatFactory());
		model.serialize(new BufferedOutputStream(new FileOutputStream(modelPath)));
	}
	
	private void setSampleStream() throws IOException {
		sampleStream = new DocumentSampleStream(new PlainTextByLineStream(inputStream, StandardCharsets.UTF_8));
	}

	/**
	 * Reads the data set from datasetPath
	 */
	private void setInputStream() {
		inputStream = new InputStreamFactory() {
			@Override
			public InputStream createInputStream() throws IOException {
				return new FileInputStream(datasetPath);
			}
		};
	}

	/**
	 * Set the parameters for training
	 * 
	 * Tinker with cut-off to get better results
	 */
	private void setTrainingParams() {
		trainParams = new TrainingParameters();
		trainParams.put(TrainingParameters.ALGORITHM_PARAM, "MAXENT");
		trainParams.put(TrainingParameters.TRAINER_TYPE_PARAM, EventTrainer.EVENT_VALUE);
		trainParams.put(TrainingParameters.ITERATIONS_PARAM, 100);
		trainParams.put(TrainingParameters.CUTOFF_PARAM, 0);
	}

}
