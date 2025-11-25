package org.com.programming.login.entities.DTO;

import org.com.programming.login.entities.AddressEntity;

public record PartyReadResponse (String nameParty, String phoneParty, String numberParty, AddressEntity addressEntity){
}
