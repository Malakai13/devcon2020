package org.example.repository;

import org.example.model.Ballot;

import java.util.List;

public interface CustomBallotRepository {

	List<Ballot> ballotsByPollId(int pollId);
}
