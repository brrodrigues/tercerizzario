/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tercerizzario.service.supplier.entity.Supplier;
import tercerizzario.service.supplier.repository.DefaultRepository;
import tercerizzario.service.supplier.repository.SupplierRepositoryCustom;

/**
 *
 * @author bruno
 */
@RestController
@RequestMapping("/suppliers")
public class SupplierService {

    private static final Logger LOG = Logger.getLogger(SupplierService.class.getName());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private DefaultRepository defaultRepository;

    @Autowired
    private SupplierRepositoryCustom supplierRepositoryCustom;

    @RequestMapping(method = {RequestMethod.GET})
    public List<Supplier> getSuppliers() {
        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        LOG.log(Level.INFO, "Call /suppliers from {0}:{1} ", new Object[]{localServiceInstance.getHost(), localServiceInstance
            .getPort()});

        return defaultRepository.findAll();
    }

    @RequestMapping(method = {RequestMethod.POST})
    public Supplier createOne(@RequestBody Supplier supplier) {
        return defaultRepository.insert(supplier);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Supplier getOne(@PathVariable(value = "id") String id) {
        return defaultRepository.findOne(id);
    }

    @RequestMapping(value = "/search/byEmail", method = {RequestMethod.GET})
    public Supplier findByEmail(@RequestAttribute(name = "email", required = true) String email) {
        return defaultRepository.findFirstByEmail(email);
    }

    @RequestMapping(method = {RequestMethod.PUT})
    public Supplier updateSupplier(@RequestBody Supplier supplier) {
        return defaultRepository.insert(supplier);
    }
//

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public ResponseEntity delete(@PathVariable(value = "id") String id) {
        defaultRepository.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
