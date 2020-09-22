package org.example.query;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.example.model.Poll;
import org.example.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PollQuery {

	@Autowired
	PollRepository pollRepository;

	@GraphQLQuery
	public Iterable<Poll> polls() {
		return pollRepository.findAll();
	}
}
