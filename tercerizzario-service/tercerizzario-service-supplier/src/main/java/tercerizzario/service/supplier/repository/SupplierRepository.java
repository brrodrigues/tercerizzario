/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package tercerizzario.service.supplier.repository;

import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.repository.query.Param;
import tercerizzario.service.supplier.entity.Supplier;

/**
 *
 * @author bruno
 */
public interface SupplierRepository extends MongoRepository<Supplier, String> {

    public Collection<Supplier> findByName(@Param(value = "name") String name);

    public Collection<Supplier> findByAddress(@Param(value = "address") String address);
}
