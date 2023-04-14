package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.GameRecord;
import com.kenzie.appserver.service.model.Game;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
public interface GameRepository extends CrudRepository<GameRecord, String> {
}
