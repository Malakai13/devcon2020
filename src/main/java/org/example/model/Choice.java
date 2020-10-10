package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
@Table
public class Choice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer choiceId;

	@Column
	Integer pollId;

	@Column
	String description;
}
