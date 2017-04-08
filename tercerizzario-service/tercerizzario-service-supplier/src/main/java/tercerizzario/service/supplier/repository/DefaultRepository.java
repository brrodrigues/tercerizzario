/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package tercerizzario.service.supplier.repository;

import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import tercerizzario.service.supplier.entity.Supplier;

/**
 *
 * @author bruno
 */
public interface DefaultRepository extends MongoRepository<Supplier, String> {

    //Supports native JSON query string
    @Query("{email:'?0'}")
    public Supplier findCustomByEmail(String email);
    
    public Collection<Supplier> findByName(String name);

    public Collection<Supplier> findByAddress(String address);

    public Supplier findFirstByEmail(String email);
}
