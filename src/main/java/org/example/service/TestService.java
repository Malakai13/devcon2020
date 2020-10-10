package org.example.service;

import org.example.model.Ballot;
import org.example.model.Choice;
import org.example.model.Poll;
import org.example.model.Voter;
import org.example.repository.BallotRepository;
import org.example.repository.ChoiceRepository;
import org.example.repository.PollRepository;
import org.example.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class TestService {

	@Autowired
	private PollRepository pollRepository;
	@Autowired
	private ChoiceRepository choiceRepository;
	@Autowired
	private VoterRepository voterRepository;
	@Autowired
	private BallotRepository ballotRepository;

	public void insertTestData() {

		final String favoriteAnimal = "Favorite Animal";

		Iterable<Poll> polls = pollRepository.saveAll(List.of(
			new Poll(null, "Favorite Color", Set.of()),
			new Poll(null, "Favorite Ice Cream", Set.of()),
			new Poll(null, favoriteAnimal, Set.of(
				Choice.builder().description("Dog").build(),
				Choice.builder().description("Cat").build(),
				Choice.builder().description("Llama").build(),
				Choice.builder().description("Panda").build(),
				Choice.builder().description("Orca").build(),
				Choice.builder().description("Rabbit").build()
			))
		));

		Poll favoriteAnimalPoll = StreamSupport.stream(polls.spliterator(), false)
			.filter(poll -> Objects.equals(poll.getName(), favoriteAnimal))
			.findFirst()
			.orElseThrow();

		Iterable<Voter> voters = voterRepository.saveAll(
			List.of(
				Voter.builder().name("A").build(),
				Voter.builder().name("B").build(),
				Voter.builder().name("C").build(),
				Voter.builder().name("D").build(),
				Voter.builder().name("E").build()
			)
		);

		List<Integer> choiceIds = favoriteAnimalPoll.getChoices().stream()
			.map(Choice::getChoiceId)
			.collect(Collectors.toList());

		List<Ballot> ballots = new ArrayList<>();
		for (Voter voter : voters) {
			Collections.shuffle(choiceIds); //Randomly choose animals

			for (int i = 0; i < choiceIds.size(); i++) {
				Integer choiceId = choiceIds.get(i);

				Ballot ballot = new Ballot(voter.getVoterId(), choiceId, i);
				ballots.add(ballot);
			}

		}

		ballotRepository.saveAll(ballots);
	}
}
