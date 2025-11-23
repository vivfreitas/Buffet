package org.com.programming.login.entities.DTO;

import org.com.programming.login.entities.AddressEntity;

public record PartyCreateResponse(Long idParty, String nameParty, String phoneParty, AddressEntity addressEntity, Long idUser, String buffeUser){
}
