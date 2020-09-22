package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table
public class Poll {

	@Id
	@GeneratedValue
	Integer pollId;

	@Column
	String name;

	@OneToMany
	@JoinColumn(name = "pollId", referencedColumnName = "pollId")
	Set<Choice> choices;
}
