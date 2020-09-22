package org.example.repository;

import org.example.model.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends CrudRepository<Voter, Integer> {}
