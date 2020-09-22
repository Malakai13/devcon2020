package org.example.repository;

import org.example.model.Choice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends CrudRepository<Choice, Integer> {}
