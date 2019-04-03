/**
 * 
 */
package com.blendin.nlp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Abhishek1.Singhal
 *
 */

@Entity
@Table(name = "session_intents")
public class SessionIntent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "probability")
	private int probability;

	@Column(name = "unique_id")
	private String uniqueId;

	@Column(name = "input_text")
	private String inputText;

	@Column(name = "completed")
	private boolean completed;

	@Column(name = "updated_on")
	private Date updatedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intent_id")
	private Intent intent;

	@OneToMany(mappedBy = "sessionIntent", fetch = FetchType.EAGER)
	private List<SessionSlot> sessionSlots;
}
