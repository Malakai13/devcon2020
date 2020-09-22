package org.example.query;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.example.model.Choice;
import org.example.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChoiceQuery {

	@Autowired
	ChoiceRepository choiceRepository;

	@GraphQLQuery
	public Iterable<Choice> choices() {
		return choiceRepository.findAll();
	}
}
