/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package tercerizzario.service.supplier.jpa.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import tercerizzario.service.supplier.jpa.domain.Supplier;

/**
 *
 * @author bruno
 */

public interface SupplierRepository extends JpaRepository<Supplier, String> {
//public interface SupplierRepository {

    public Collection<Supplier> findByName(String name);
}
