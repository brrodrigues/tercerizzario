package tercerizzario.service.supplier.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author bruno
 */
@Document(collection = "suppliers")
@CompoundIndex(name = "supplier.location", def = "{location : '2dsphere'}")
public class Supplier implements Serializable {

    @Id
    private String id;
    private String name;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
//    @Indexed
//    private GeoJsonPoint location;

    private Double[] location;
//    private Double[] locations;
    private String cellPhone;
    private String address;
    @TextIndexed
    private String email;
    private String document;

    public Supplier() {
    }

    public Supplier(String id, String name, Double[] location, String cellPhone, String address, String email, String document) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cellPhone = cellPhone;
        this.address = address;
        this.email = email;
        this.document = document;
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

//    public GeoJsonPoint getLocation() {
//        return location;
//    }
//
//    public void setLocation(GeoJsonPoint location) {
//        this.location = location;
//    }
    public Double[] getLocation() {
        return location;
    }

//    public Double[] getLocations() {
//        return locations;
//    }
//
//    public void setLocations(Double[] locations) {
//        this.locations = locations;
//    }

    public void setLocation(Double[] location) {
        this.location = location;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", name=" + name + ", location=" + location + ", cellPhone=" + cellPhone + ", address=" + address + ", email=" + email + ", document=" + document + '}';
    }

}
