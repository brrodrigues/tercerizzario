/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package tercerizzario.service.supplier.repository;

import tercerizzario.tercerizzario.commons.lib.domain.Supplier;

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
    Supplier upsert(Supplier supplier);

}
