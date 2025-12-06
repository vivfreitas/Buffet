package org.com.programming.login.controllers;

import org.com.programming.login.entities.DTO.PartyCreateResponse;
import org.com.programming.login.entities.DTO.PartyReadResponse;
import org.com.programming.login.entities.PartyEntity;
import org.com.programming.login.service.PartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("party")
public class PartyController {

    private final PartyService partyService;
    public PartyController(PartyService partyService){
        this.partyService = partyService;
    }

    @PostMapping("create")
    public ResponseEntity<Object> createParty(@RequestBody PartyEntity partyEntity){
        PartyCreateResponse service = partyService.createParty(partyEntity);

        if (service == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CEP Não encontrado.");
        }
        return ResponseEntity.ok(service);
    }

    @GetMapping("read")
    public ResponseEntity<List<PartyReadResponse>> read(){
        List<PartyReadResponse> obj = partyService.readAll();
        return ResponseEntity.ok(obj);
    }
}
