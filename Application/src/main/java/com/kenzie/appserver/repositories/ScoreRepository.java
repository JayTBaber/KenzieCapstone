package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Score;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ScoreRepository extends CrudRepository<Score, Long> {
}
