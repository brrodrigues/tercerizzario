/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.GeoNearOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;
import tercerizzario.tercerizzario.commons.lib.domain.Supplier;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Supplier> findCustomLocationNear(double longitude, double latitude, double distance) {

//        Query query = new Query();
//        Criteria criteria = new Criteria("location");
//        criteria.nearSphere(new Point(longitude, latitude)).maxDistance(distance);
//        query.addCriteria(criteria).skip(1);
//        List<Supplier> aggregationResults = mongoTemplate.find(query, Supplier.class);
//        return aggregationResults;
        GeoNearOperation geoNear = Aggregation.geoNear(NearQuery.near(new Point(longitude, latitude)).spherical(true).maxDistance(distance, Metrics.KILOMETERS), "locations");
        SkipOperation skip = Aggregation.skip(1L);
        Fields fields = Fields.fields("id", "name", "location", "cellPhone", "address", "email", "document");
        ProjectionOperation projectionOperation;
        projectionOperation = Aggregation.project(fields);
        TypedAggregation<Supplier> newAggregation = Aggregation.newAggregation(Supplier.class, geoNear, skip, projectionOperation);
        AggregationResults<Supplier> aggregationResults=  mongoTemplate.aggregate(newAggregation, "suppliers", Supplier.class);

        return aggregationResults.getMappedResults();

    }

}
