package org.com.programming.login.controllers;

import org.com.programming.login.entities.DTO.PartyCreateResponse;
import org.com.programming.login.entities.DTO.PartyReadResponse;
import org.com.programming.login.entities.PartyEntity;
import org.com.programming.login.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("read")
    public ResponseEntity<List<PartyReadResponse>> read(){
        List<PartyReadResponse> obj = partyService.readAll();
        return ResponseEntity.ok(obj);
    }
}
