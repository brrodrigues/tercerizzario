/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.profession.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import tercerizzario.service.profession.entity.Profession;

/**
 *
 * @author bruno
 */
public interface ProfessionRepository extends MongoRepository<Profession, String>{
    
    public List<Profession> findByName(String name);
    
}
