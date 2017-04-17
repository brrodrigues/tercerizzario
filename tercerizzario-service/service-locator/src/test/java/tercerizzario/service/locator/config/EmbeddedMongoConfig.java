/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.config;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 *  
 * @author bruno
 */
//@Configuration
//@EnableMongoRepositories
public class EmbeddedMongoConfig {

    private static final String DB_NAME = "integrationTest";
    private static final int DB_PORT = 27017;
    private static final String DB_HOST = "localhost";
    private static final String SUPPLIER_COLLECTION = "suppliers";

    private MongodExecutable mongodExecutable = null;

//    @Bean(name = "mongoClient")
    public MongoClient mongoClient() throws IOException {
        // Lots of calls here to de.flapdoodle.embed.mongo code base to 
        // create an embedded db and insert some JSON data
        LOG.log(Level.INFO, "Starting embeddeded mongodb");
        MongoClient mongoClient = new MongoClient(DB_HOST, DB_PORT);

        return mongoClient;

    }
    private static final Logger LOG = Logger.getLogger(EmbeddedMongoConfig.class.getName());

//    @Autowired
//    @Bean(name = "mongoDbFactory")
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        LOG.log(Level.INFO, "Starting embeddeded mongodb factory");
        return new SimpleMongoDbFactory(mongoClient, DB_NAME);
    }

//    @Autowired
//    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        LOG.log(Level.INFO, "Starting mongodb template");
        return new MongoTemplate(mongoClient, DB_NAME);
    }

//    @PreDestroy
    public void shutdownEmbeddedMongoDB() {
        LOG.log(Level.INFO, "Stoping mongodb server");
        if (this.mongodExecutable != null) {
            this.mongodExecutable.stop();
        }
    }

}
