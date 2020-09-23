package org.example.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.model.Ballot;
import org.example.model.QBallot;
import org.example.model.QChoice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BallotRepositoryImpl implements CustomBallotRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Ballot> ballotsByPollId(int pollId) {
		QChoice choice = QChoice.choice;
		QBallot ballot = QBallot.ballot;
		return new JPAQueryFactory(entityManager)
			.selectFrom(ballot)
			.join(choice)
			.on(choice.choiceId.eq(ballot.choiceId))
			.where(choice.pollId.eq(pollId))
			.fetch();
	}
}
