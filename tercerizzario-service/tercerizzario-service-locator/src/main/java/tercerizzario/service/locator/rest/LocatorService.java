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
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tercerizzario.service.locator.repository.DefaultRepository;
import tercerizzario.tercerizzario.commons.lib.domain.Supplier;

/**
 *
 * @author bruno
 */
@RestController
@RequestMapping(value = "/locator")
public class LocatorService {

    @Autowired
    private DefaultRepository customRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Supplier> findByLocationNear(@RequestParam(value = "x") Double longitudeX, @RequestParam(value = "y") Double latitudeY, @RequestParam(value = "z") Double distanceZ) {
        LOG.log(Level.INFO, "GET {0} ", new Object[]{"/locator"});

        Point point = new Point(longitudeX, latitudeY);
        Distance distance = new Distance(distanceZ, Metrics.KILOMETERS);

        LOG.log(Level.INFO, "Value {0} {1}", new Object[]{distance.getValue(), distance.getMetric()});

        return customRepository.findByLocationNear(point, distanceZ);

    }
    private static final Logger LOG = Logger.getLogger(LocatorService.class.getName());

}
