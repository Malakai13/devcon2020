package org.example.service;

import org.example.model.Ballot;
import org.example.repository.BallotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BallotService {

	@Autowired
	BallotRepository ballotRepository;

	public Integer instantRunOff(int pollId, double percentage) {
		if (percentage < 0 || percentage > 1) {
			throw new IllegalArgumentException("Winning percentage must be between 0% and 100%");
		}

		List<Ballot> ballots = ballotRepository.ballotsByPollId(pollId);
		if (ballots.isEmpty()) {
			throw new IllegalArgumentException("There are no ballots for that poll");
		}

		Integer winningChoiceId = null;
		Set<Integer> choicesThatHaveLost = new HashSet<>();

		return null;
	}

	private static Map<Integer, Integer> topPickByVoter(Collection<Ballot> ballots) {
		Map<Integer, List<Ballot>> byVoter = ballots.stream()
			.collect(Collectors.groupingBy(Ballot::getVoterId));

		return null;
	}
}
