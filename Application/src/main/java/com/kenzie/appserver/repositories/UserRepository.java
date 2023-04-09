package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@EnableScan
public interface UserRepository extends CrudRepository<UserRecord, String> {
}
