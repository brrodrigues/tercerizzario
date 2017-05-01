/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.repository;

import java.util.List;
import tercerizzario.tercerizzario.commons.lib.domain.Supplier;

/**
 *
 * @author bruno
 */
public interface CustomRepository {

    List<Supplier> findCustomLocationNear(double longitude, double latitude, double distance);

}
