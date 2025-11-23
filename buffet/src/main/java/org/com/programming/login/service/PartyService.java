package org.com.programming.login.service;

import org.com.programming.login.entities.AddressEntity;
import org.com.programming.login.entities.DTO.AddressResponse;
import org.com.programming.login.entities.DTO.PartyCreateResponse;
import org.com.programming.login.entities.PartyEntity;
import org.com.programming.login.entities.UserEntity;
import org.com.programming.login.infra.restTemplate.ViaCepRest;
import org.com.programming.login.jpa.AddressRepository;
import org.com.programming.login.jpa.PartyRepository;
import org.com.programming.login.jpa.UserJpa;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PartyService {

    public final AddressRepository addressRepository;
    public final UserJpa userJpa;
    public final PartyRepository partyRepository;
    public final ViaCepRest viaCepRest;

    public PartyService(AddressRepository addressRepository, UserJpa userJpa, PartyRepository partyRepository, ViaCepRest viaCepRest) {
        this.addressRepository = addressRepository;
        this.userJpa = userJpa;
        this.partyRepository = partyRepository;
        this.viaCepRest = viaCepRest;
    }

    //    CREATE PARTY
    public PartyCreateResponse createParty(PartyEntity partyEntity){

        /* Pega as informações do usuário daquele Token. */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userAuth = (UserEntity) auth.getPrincipal();

        AddressResponse address = viaCepRest.consultaCEP(partyEntity.getCep());
        if (address == null){
            return null;
        }
        if (!auth.isAuthenticated()){
            return null;
        }

        AddressEntity addressEntity = mapAddressEntity(address);
        addressEntity.setNumberHome(partyEntity.getNumberParty());
        AddressEntity repositoryAd = addressRepository.save(addressEntity);

        partyEntity.setAddress(repositoryAd);
        partyEntity.setUsers(userAuth);
        partyRepository.save(partyEntity);

        return new PartyCreateResponse(
                partyEntity.getIdParty(),
                partyEntity.getNameParty(),
                partyEntity.getPhoneParty(),
                partyEntity.getAddress(),
                partyEntity.getUsers().getIdUser(),
                partyEntity.getUsers().getNameUser()
        );
    }

    private AddressEntity mapAddressEntity(AddressResponse response) {
        /* Mapeamento de endereço:
        * Chamar o consultaCEP, colocar o resultado aqui dentro
        * e depois chamar a hibernate address.*/
        AddressEntity address = new AddressEntity();
        // Mapeamento manual dos campos
        address.setZip_code(response.getCep());
        address.setStreet(response.getLogradouro());
        address.setAddress_details(response.getRegiao());
        address.setNeighborhood(response.getBairro());
        address.setCity(response.getLocalidade());
        return address;
    }
}
