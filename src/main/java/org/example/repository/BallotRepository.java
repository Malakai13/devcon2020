package org.example.repository;

import org.example.model.Ballot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotRepository extends CrudRepository<Ballot, Ballot.BallotId>, CustomBallotRepository {}
