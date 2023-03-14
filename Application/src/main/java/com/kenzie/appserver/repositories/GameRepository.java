package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Game;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface GameRepository extends CrudRepository<Game, Long> {
}
