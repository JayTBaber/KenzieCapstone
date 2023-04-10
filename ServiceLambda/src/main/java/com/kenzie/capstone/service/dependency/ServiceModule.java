package com.kenzie.capstone.service.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.capstone.service.LambdaService;
import com.kenzie.capstone.service.dao.ExampleDao;

import com.kenzie.capstone.service.dao.GameDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(
        includes = DaoModule.class
)
public class ServiceModule {

    @Singleton
    @Provides
    public LambdaService provideLambdaService(
            GameDao gameDao,
            @Named("ExampleDao") ExampleDao exampleDao
    ) {
        return new LambdaService(exampleDao, gameDao);
    }

    @Provides
    public GameDao provideGameDao(@Named("DynamoDBMapper") DynamoDBMapper mapper) {
        return new GameDao(mapper);
    }
}

