package org.com.programming.login.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique = true)
    private String nameUser;
    private String passwordUser;
    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<PartyEntity> partyEntities;

    public UserEntity(){}

    public UserEntity(Long idUser, String nameUser, List<PartyEntity> partyEntities, String passwordUser) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.passwordUser = passwordUser;
        this.partyEntities = partyEntities;
    }

    public List<PartyEntity> getPartyEntities() {
        return partyEntities;
    }

    public void setPartyEntities(List<PartyEntity> partyEntities) {
        this.partyEntities = partyEntities;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.passwordUser;
    }

    @Override
    public String getUsername() {
        return this.nameUser;
    }
}
