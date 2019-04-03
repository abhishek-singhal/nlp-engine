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
@Table(name = "criteria")
public class Criteria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "regex")
	private String regex;

	@Column(name = "dictionary")
	private String dictionary;

	@Column(name = "ner_model")
	private String nerModel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_id")
	private Slot slot;
}
