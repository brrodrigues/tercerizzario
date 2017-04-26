/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.locator.domain.proxy;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Fake class references supplier class located into supplier service
 *
 * @author bruno
 */
@Document(collection = "supplier")
public class Supplier {

    @Id
    private String id;
    private String name;
    @Field("location")
    private double[] location;
    private Date lastUpdatePosition;

    public Supplier() {
    }

    public Supplier(String name, double[] location) {
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public Date getLastUpdatePosition() {
        return lastUpdatePosition;
    }

    public void setLastUpdatePosition(Date lastUpdatePosition) {
        this.lastUpdatePosition = lastUpdatePosition;
    }

}
