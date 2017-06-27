/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.profession.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tercerizzario.service.profession.domain.Profession;

/**
 *
 * @author bruno
 */
@RepositoryRestResource
public interface DefaultRepository extends MongoRepository<Profession, String> {
    
    @Query("{name : { $regex: '?0', $options: 'i'}}")
    public List<Profession> findSimilarByName(@Param(value = "name") String name);

}
