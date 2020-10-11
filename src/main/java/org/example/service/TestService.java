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

import java.util.*;
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

		List<Poll> pollList = new ArrayList<>();
		pollList.addAll(List.of(
				new Poll(null, "Favorite Color", Set.of()),
				new Poll(null, "Favorite Ice Cream", Set.of()),
				new Poll(null, favoriteAnimal, Set.of())
		));
		Iterable<Poll> polls = pollRepository.saveAll(pollList);

		Poll favoriteAnimalPoll = StreamSupport.stream(polls.spliterator(), false)
			.filter(poll -> Objects.equals(poll.getName(), favoriteAnimal))
			.findFirst()
			.orElseThrow();

		Integer favoriteAnimalPollId = favoriteAnimalPoll.getPollId();
		Choice.ChoiceBuilder choiceBuilder = Choice.builder().pollId(favoriteAnimalPollId);

		Set<Choice> favoriteAnimalChoices = new HashSet<>();
		favoriteAnimalChoices.addAll(Set.of(
				choiceBuilder.description("Dog").build(),
				choiceBuilder.description("Cat").build(),
				choiceBuilder.description("Llama").build(),
				choiceBuilder.description("Panda").build(),
				choiceBuilder.description("Orca").build(),
				choiceBuilder.description("Rabbit").build()
		));

		Iterable<Choice> choices = choiceRepository.saveAll(favoriteAnimalChoices);

		Iterable<Voter> voters = voterRepository.saveAll(
			List.of(
				Voter.builder().name("A").build(),
				Voter.builder().name("B").build(),
				Voter.builder().name("C").build(),
				Voter.builder().name("D").build(),
				Voter.builder().name("E").build()
			)
		);

		List<Integer> choiceIds = StreamSupport.stream(choices.spliterator(), false)
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
