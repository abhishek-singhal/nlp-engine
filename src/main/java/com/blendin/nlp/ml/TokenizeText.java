/**
 * 
 */
package com.blendin.nlp.ml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * @author Abhishek1.Singhal
 *
 */

public class TokenizeText {

//	@Value("${path.tokenizeModel}")
	private String tokenizeModelPath = "D:/BlendIn/email/en-token.bin";
	
	private InputStream tokenizeModelFile;
	
	/**
	 * Breaks the input text in small parts called tokens
	 * 
	 * @param inputText
	 * @return String Array of tokens
	 * @throws IOException
	 */
	public String[] tokenize(String inputText) throws IOException {
		tokenizeModelFile = new FileInputStream(tokenizeModelPath);
		return new TokenizerME(new TokenizerModel(tokenizeModelFile)).tokenize(inputText);
	}
}
