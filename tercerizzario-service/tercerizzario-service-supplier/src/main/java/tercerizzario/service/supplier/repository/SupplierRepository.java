/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package tercerizzario.service.supplier.repository;

import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tercerizzario.service.supplier.domain.Supplier;

/**
 *
 * @author bruno
 */
@RepositoryRestResource(path = "supplier-service")
public interface SupplierRepository extends MongoRepository<Supplier, String> {

    //Supports native JSON query string
    @Query(value = "{email:'?0'}", fields = "{id : 1, name : 1, email : 1, cellPhone : 1, address : 1, document : 1}")
    public Supplier findCustomByEmail(String email);

    @Query(fields = "{id : 1, name : 1, email : 1, cellPhone : 1, address : 1, document : 1}")
    public Collection<Supplier> findByName(String name);

//    @Query(fields = "{id : 1, name : 1, email : 1, cellPhone : 1, address : 1, document : 1}")
    public Supplier findFirstByEmail(String email);

}
