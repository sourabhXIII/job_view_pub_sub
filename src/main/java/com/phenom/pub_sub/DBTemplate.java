package com.phenom.pub_sub;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class DBTemplate {

    public
    MongoDbFactory mongoDbFactory(String dbName) throws Exception {

        if(dbName == null)
            dbName = "testDB";
        return new SimpleMongoDbFactory(new MongoClient("localhost"), dbName);
    }

    public @Bean
    MongoTemplate connection() throws Exception {
        return new MongoTemplate(mongoDbFactory("testDB"));
    }
}