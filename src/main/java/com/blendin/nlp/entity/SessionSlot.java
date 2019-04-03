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
@Table(name = "session_slots")
public class SessionSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "value")
	private String value;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_id")
	private Slot slot;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "session_intent_id")
	private SessionIntent sessionIntent;
}
