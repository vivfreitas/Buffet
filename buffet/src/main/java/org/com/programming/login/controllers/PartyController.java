package org.com.programming.login.controllers;

import org.com.programming.login.entities.DTO.PartyCreateResponse;
import org.com.programming.login.entities.PartyEntity;
import org.com.programming.login.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("party")
public class PartyController {

    private final PartyService partyService;
    public PartyController(PartyService partyService){
        this.partyService = partyService;
    }

    @PostMapping("create")
    public ResponseEntity<PartyCreateResponse> createParty(@RequestBody PartyEntity partyEntity){
        PartyCreateResponse service = partyService.createParty(partyEntity);
        return ResponseEntity.ok(service);
    }
}
