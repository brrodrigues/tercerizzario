/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tercerizzario.tercerizzario.commons.lib.domain.Supplier;

/**
 *
 * @author bruno
 */
public interface DefaultRepository extends MongoRepository<Supplier, String>{
    
}
