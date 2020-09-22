package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity
@Table
@IdClass(Ballot.BallotId.class)
public class Ballot {

	@Id
	Integer voterId;

	@Id
	Integer choiceId;

	@Column
	Integer place;

	@Value
	public static class BallotId implements Serializable {
		Integer voterId;
		Integer choiceId;
	}
}
