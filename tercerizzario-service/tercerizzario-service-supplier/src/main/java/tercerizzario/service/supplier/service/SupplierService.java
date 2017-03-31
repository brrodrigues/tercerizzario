/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tercerizzario.service.supplier.jpa.domain.Supplier;
import tercerizzario.service.supplier.jpa.repository.SupplierRepository;

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
    private SupplierRepository supplierRepository;

    @RequestMapping(method = {RequestMethod.GET})
    public List<Supplier> getSuppliers() {

        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        LOG.log(Level.INFO, "Call /suppliers from {0}:{1} ", new Object[]{localServiceInstance.getHost(), localServiceInstance
            .getPort()});

        return supplierRepository.findAll();
//        return "Foi";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public Supplier createOne(@RequestBody Supplier supplier) {
        return supplierRepository.saveAndFlush(supplier);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Supplier getOne(@PathVariable(value = "id") String id) {
        return supplierRepository.findOne(id);
    }

    @RequestMapping(method = {RequestMethod.PUT})
    public Supplier saveSupplier(@RequestBody Supplier supplier) {
        return supplierRepository.saveAndFlush(supplier);
    }
//

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public ResponseEntity delete(@PathVariable(value = "id") String id) {
        supplierRepository.delete(id);
        return new ResponseEntity("OK", HttpStatus.ACCEPTED);
    }
}
