package org.example.query;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.example.model.Poll;
import org.example.model.Voter;
import org.example.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoterQuery {
	@Autowired
	VoterRepository voterRepository;

	@GraphQLQuery
	public Iterable<Voter> voters() {
		return voterRepository.findAll();
	}
}
