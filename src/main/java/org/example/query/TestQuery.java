package org.example.query;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Component;

@Component

public class TestQuery {

	@GraphQLQuery
	public String getHelloWorld(String name) {
		return "Hello, " + name + "!";
	}
}
