/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.profession.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tercerizzario.service.profession.entity.Profession;

/**
 *
 * @author bruno
 */
@RepositoryRestResource(collectionResourceRel = "profession", path = "professions")
public interface ProfessionRepository extends MongoRepository<Profession, String> {

    public List<Profession> findByName(@Param(value = "name") String name);

}
