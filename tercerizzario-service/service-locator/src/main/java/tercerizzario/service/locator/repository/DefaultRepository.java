/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.repository;

import java.util.List;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import tercerizzario.service.locator.domain.proxy.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author bruno
 */
public interface DefaultRepository extends MongoRepository<Supplier, String> {
    
    public List<Supplier> findByLocationNear(Point point, Distance distance);
    
}
