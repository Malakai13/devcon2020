package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table
public class Choice {

	@Id
	@GeneratedValue
	Integer choiceId;

	@Column
	Integer pollId;

	@Column
	String description;
}
