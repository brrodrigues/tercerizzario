/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package tercerizzario.service.supplier.jpa.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tercerizzario.service.supplier.jpa.domain.Supplier;

/**
 *
 * @author bruno
 */
@RepositoryRestResource(collectionResourceRel = "suppliers", path = "suppliers")
public class SupplierRepository implements MongoRepository<Supplier, String> {

    public Collection<Supplier> findByName(String name);

    public Collection<Supplier> findByAddress(@Param(value = "address") String address);
}
