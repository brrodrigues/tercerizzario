/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.profession.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.server.PathParam;
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
import tercerizzario.service.profession.domain.Profession;
import tercerizzario.service.profession.repository.DefaultRepository;

/**
 *
 * @author bruno
 */
@RestController
@RequestMapping("/professions")
public class ProfessionService {

    private static final Logger LOG = Logger.getLogger(ProfessionService.class.getName());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private DefaultRepository repository;

    @RequestMapping(method = {RequestMethod.GET})
    public List<Profession> getSuppliers() {

        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        LOG.log(Level.INFO, "Call {2} from server {0}:{1} ", new Object[]{localServiceInstance.getHost(), localServiceInstance
            .getPort(), localServiceInstance.getUri().getPath()});

        return repository.findAll();
    }

    @RequestMapping(value = "/search/bySimilarName", method = {RequestMethod.GET})
    public List<Profession> findProfessionSimilarByName(@PathParam(value = "name") String name) {
        LOG.log(Level.INFO, "call search/bySimilarName {0}", name);
        return repository.findSimilarByName(name);
    }

    @RequestMapping(method = {RequestMethod.POST})
    public Profession createOne(@RequestBody Profession profession) {
        return repository.insert(profession);
    }

    @RequestMapping(method = {RequestMethod.PUT})
    public Profession updateProfession(@RequestBody Profession profession) {
        LOG.log(Level.INFO, "PUT / {0}", profession.toString());
        return repository.save(profession);
    }

    public ResponseEntity deleteProfession(@PathVariable(value = "id") String id) {
        repository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
