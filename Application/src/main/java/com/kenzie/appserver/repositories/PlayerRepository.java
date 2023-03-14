package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Player;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
