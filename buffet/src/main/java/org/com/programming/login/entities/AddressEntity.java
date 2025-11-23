package org.com.programming.login.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;
    private String zip_code;
    private String street;
    private String address_details;
    private String neighborhood;
    private String city;
    private String numberHome;
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private PartyEntity partyEntity;

    public AddressEntity(){}

    public AddressEntity(Long idAddress, String zip_code, String street, String address_details, String neighborhood, String city, String numberHome, PartyEntity partyEntity) {
        this.idAddress = idAddress;
        this.zip_code = zip_code;
        this.street = street;
        this.address_details = address_details;
        this.neighborhood = neighborhood;
        this.city = city;
        this.numberHome = numberHome;
        this.partyEntity = partyEntity;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress_details() {
        return address_details;
    }

    public void setAddress_details(String address_details) {
        this.address_details = address_details;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumberHome() {
        return numberHome;
    }

    public void setNumberHome(String numberHome) {
        this.numberHome = numberHome;
    }

    public PartyEntity getPartyEntity() {
        return partyEntity;
    }

    public void setPartyEntity(PartyEntity partyEntity) {
        this.partyEntity = partyEntity;
    }
}
