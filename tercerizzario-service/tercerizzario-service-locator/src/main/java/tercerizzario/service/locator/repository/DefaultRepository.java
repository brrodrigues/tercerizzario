/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.repository;

import java.util.List;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tercerizzario.tercerizzario.commons.lib.domain.Supplier;

/**
 *
 * @author bruno
 */
public interface DefaultRepository extends MongoRepository<Supplier, String> {
    
    @Query(value = "{ 'location' : { '$near' : ['point.x', 'point.y'], '$maxDistance' : 'distance'}}")
    public List<Supplier> findByLocationNear(Point point, double distance);
    
}
