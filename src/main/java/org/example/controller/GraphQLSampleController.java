package org.example.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import lombok.Value;
import org.example.query.TestQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class GraphQLSampleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLSampleController.class);

	private final GraphQL graphQL;

	@Autowired
	public GraphQLSampleController(
		TestQuery testQuery
	) {
		//Schema generated from query classes
		GraphQLSchema schema = new GraphQLSchemaGenerator()
			.withBasePackages("org.example")
			.withOperationsFromSingletons(testQuery)
			.generate();
		graphQL = GraphQL.newGraphQL(schema).build();

		LOGGER.info("Generated GraphQL schema using SPQR");
	}

	@PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> graphql(@RequestBody GraphQlInput graphQlInput, HttpServletRequest raw) {
		ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
			.query(graphQlInput.getQuery())
			.operationName(graphQlInput.getOperationName())
			.variables(graphQlInput.getVariables())
			.context(raw)
			.build()
		);
		return executionResult.toSpecification();
	}

	@Value
	private static class GraphQlInput {
		String query;
		String operationName;
		Map<String, Object> variables;
	}
}
