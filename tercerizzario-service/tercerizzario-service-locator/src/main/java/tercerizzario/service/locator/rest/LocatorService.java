/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tercerizzario.service.locator.domain.Locator;
import tercerizzario.service.locator.domain.proxy.Supplier;
import tercerizzario.service.locator.repository.DefaultRepository;

/**
 *
 * @author bruno
 */
@RestController
@RequestMapping(value = "/locator")
public class LocatorService {

    @Autowired
    private DefaultRepository customRepository;

    @RequestMapping(method = RequestMethod.POST)
    public List<Supplier> findByLocationNear(@RequestBody Locator locator) {
        LOG.log(Level.INFO, "POST /locator");
        
        Point point = new Point(locator.getX(), locator.getY());
        Distance distance = new Distance(locator.getDistance());
        
        return customRepository.findByLocationNear(point, distance);
        
    }
    private static final Logger LOG = Logger.getLogger(LocatorService.class.getName());

}
