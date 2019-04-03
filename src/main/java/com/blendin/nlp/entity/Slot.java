/**
 * 
 */
package com.blendin.nlp.entity;

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
@Table(name = "slots")
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "slot_name")
	private String slotName;

	@Column(name = "question")
	private String question;

	@Column(name = "strict")
	private boolean strict;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intent_id")
	private Intent intent;

	@OneToMany(mappedBy = "slot", fetch = FetchType.LAZY)
	private List<Criteria> criterias;
	
	@OneToMany(mappedBy = "slot", fetch = FetchType.LAZY)
	private List<SessionSlot> sessionSlots;
}
