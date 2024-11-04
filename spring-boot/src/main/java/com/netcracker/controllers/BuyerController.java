package com.netcracker.controllers;

import com.hazelcast.core.HazelcastInstance;
import com.netcracker.model.Buyer;
import com.netcracker.repository.BuyerRepository;
import com.netcracker.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class BuyerController {

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    HazelcastInstance hazelcastInstance;

    @GetMapping("/buyers")
    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    @GetMapping("/buyers/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Buyer buyer;
        if (hazelcastInstance != null) {
            buyer = (Buyer) hazelcastInstance.getMap("data").get(id);
        } else {
            buyer = buyerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Buyer not found for this id :: " + id));
        }
        return ResponseEntity.ok().body(buyer);
    }

    @PostMapping("/buyers")
    public Buyer createBuyer(@Valid @RequestBody Buyer buyer) {
        if (hazelcastInstance != null) {
            hazelcastInstance.getMap("data").put(buyer.getId(), buyer);
        }
        return buyerRepository.save(buyer);
    }


    @DeleteMapping("/buyers/{id}")
    public Map<String, Boolean> deleteBuyer(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Buyer buyer = buyerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer not found for this id :: " + id));

        buyerRepository.delete(buyer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/buyers")
    public Map<String, Boolean> deleteAllBuyers () {
        buyerRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

