/**
 * 
 */
package com.blendin.nlp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Abhishek1.Singhal
 *
 */

@Entity
@Table(name = "dataset")
public class Dataset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "input_text")
	private String inputText;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intent_id")
	private Intent intent;

}
