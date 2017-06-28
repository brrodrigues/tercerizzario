/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package tercerizzario.service.supplier.repository;

import tercerizzario.service.supplier.domain.SupplierDomain;


/**
 *
 * @author bruno
 */
public interface SupplierRepositoryCustom {

    /**
     *
     * @param supplier
     * @return
     */
    SupplierDomain upsert(SupplierDomain supplier);

}
