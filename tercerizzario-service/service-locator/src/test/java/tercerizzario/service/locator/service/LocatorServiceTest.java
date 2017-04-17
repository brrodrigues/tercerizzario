/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tercerizzario.service.locator.domain.Locator;
import tercerizzario.service.locator.repository.DefaultRepository;
import tercerizzario.service.locator.domain.proxy.Supplier;

/**
 *
 * @author bruno
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocatorServiceTest {

    @Autowired
    private DefaultRepository defaultRepository;

    @Autowired
    private Locator locator;

    Supplier supplier1; 
    Supplier supplier2; 
    Supplier supplier3; 

    public LocatorServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws JsonProcessingException {

        defaultRepository.deleteAll();

        supplier1 = new Supplier("Bruno", new double[]{-22.86571, -43.3456137});
        supplier2 = new Supplier("Mitchell", new double[]{-22.8676867, -43.35231220000003});
        supplier3 = new Supplier("Marlon", new double[]{-22.899244781388, -43.179649114608765});

        defaultRepository.save(supplier1);
        defaultRepository.save(supplier2);
        defaultRepository.save(supplier3);

    }

    @After
    public void tearDown() {
    }

    /**
     * When calling locator resource then return success
     */
    @Test
    public void whenLocateNearSupplier1With10km_thenReturn1Collection() {

        Point point = new Point(locator.getX(), locator.getY());
        Distance distance = new Distance(locator.getDistance());

        List<Supplier> findByLocationNear = defaultRepository.findByLocationNear(point, distance);
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertEquals("Passed", 1, findByLocationNear.size());
    }

}
