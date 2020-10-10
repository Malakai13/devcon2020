package org.example.mutation;

import io.leangen.graphql.annotations.GraphQLMutation;
import org.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestMutation {

	@Autowired
	TestService testService;

	@GraphQLMutation
	public void insertTestData() {
		testService.insertTestData();
	}
}
