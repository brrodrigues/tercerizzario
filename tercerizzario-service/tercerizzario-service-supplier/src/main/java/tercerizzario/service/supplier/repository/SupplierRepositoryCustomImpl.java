/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import tercerizzario.service.supplier.domain.Supplier;

@Qualifier
public class SupplierRepositoryCustomImpl implements SupplierRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate; 
    
    /**
     *
     * @param supplier
     * @return
     */
    @Override
    public Supplier upsert(Supplier supplier) {

        Query query = new Query(Criteria.where("id").is(supplier.getId()));
        Update update = new Update();

        if (supplier.getName() != null) {
            update.set("name", supplier.getName());
        }
        if (supplier.getEmail() != null) {
            update.set("email", supplier.getEmail());
        }
        if (supplier.getAddress() != null) {
            update.set("address", supplier.getAddress());
        }
        if (supplier.getCellPhone() != null) {
            update.set("cellPhone", supplier.getCellPhone());
        }
        if (supplier.getDocument() != null) {
            update.set("document", supplier.getDocument());
        }
        if (supplier.getLocation() != null) {
            update.set("location", supplier.getLocation());
        }
        
        Supplier findAndModify = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Supplier.class);

        return findAndModify;
    }

}
