package org.example.query;


import io.leangen.graphql.annotations.GraphQLQuery;
import org.example.model.Ballot;
import org.example.repository.BallotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BallotQuery {
	@Autowired
	BallotRepository ballotRepository;

	@GraphQLQuery
	public Iterable<Ballot> ballots() {
		return ballotRepository.findAll();
	}
}
