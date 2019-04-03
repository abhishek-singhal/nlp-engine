package com.blendin.nlp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Abhishek1.Singhal
 *
 */

@Entity
@Table(name = "intents")
public class Intent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "intent_name")
	private String name;

	@Column(name = "one_liner")
	private boolean oneLiner;

	@OneToMany(mappedBy = "intent", fetch = FetchType.LAZY)
	private List<Dataset> dataset;

	@OneToMany(mappedBy = "intent", fetch = FetchType.EAGER)
	private List<Slot> slots;

	@OneToMany(mappedBy = "intent", fetch = FetchType.LAZY)
	private List<SessionIntent> sessionIntents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOneLiner() {
		return oneLiner;
	}

	public void setOneLiner(boolean oneLiner) {
		this.oneLiner = oneLiner;
	}

}
