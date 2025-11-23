package org.com.programming.login.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_party")
public class PartyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParty;
    private String nameParty;
    private String phoneParty;
    private String cep;
    private String numberParty;
    @OneToOne( cascade = CascadeType.ALL)
    private AddressEntity address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    public PartyEntity(){}

    public PartyEntity(Long idParty, String nameParty, String phoneParty, String cep, String numberParty, AddressEntity address, UserEntity users) {
        this.idParty = idParty;
        this.nameParty = nameParty;
        this.phoneParty = phoneParty;
        this.cep = cep;
        this.numberParty = numberParty;
        this.address = address;
        this.users = users;
    }

    public Long getIdParty() {
        return idParty;
    }

    public void setIdParty(Long idParty) {
        this.idParty = idParty;
    }

    public String getNameParty() {
        return nameParty;
    }

    public void setNameParty(String nameParty) {
        this.nameParty = nameParty;
    }

    public String getPhoneParty() {
        return phoneParty;
    }

    public void setPhoneParty(String phoneParty) {
        this.phoneParty = phoneParty;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumberParty() {
        return numberParty;
    }

    public void setNumberParty(String numberParty) {
        this.numberParty = numberParty;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }
}
