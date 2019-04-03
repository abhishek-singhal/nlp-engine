/**
 * 
 */
package com.blendin.nlp.ml;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Abhishek1.Singhal
 *
 */
public class PredictIntentTest {

	private TokenizeText tokenizer;
	private PredictIntent predictIntent;
	private String inputText;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tokenizer = new TokenizeText();
		predictIntent = new PredictIntent();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.blendin.nlp.ml.PredictIntent#predict(java.lang.String[])}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testPredict1() throws IOException {
		inputText = "track my shipment";
		predictIntent.predict(tokenizer.tokenize(inputText));
		assertEquals("trackShipment", predictIntent.getIntentName());
	}

	@Test
	public void testPredict2() throws IOException {
		inputText = "nearby fedex locations";
		predictIntent.predict(tokenizer.tokenize(inputText));
		assertEquals("locator", predictIntent.getIntentName());
	}

	@Test
	public void testPredict3() throws IOException {
		inputText = "hi Fedex";
		predictIntent.predict(tokenizer.tokenize(inputText));
		assertEquals("greetings", predictIntent.getIntentName());
	}
}
