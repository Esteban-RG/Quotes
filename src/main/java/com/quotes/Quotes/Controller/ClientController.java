package com.quotes.Quotes.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quotes.Quotes.DTO.Client.ClientCreateDTO;
import com.quotes.Quotes.DTO.Client.ClientDTO;
import com.quotes.Quotes.Model.Client;
import com.quotes.Quotes.Services.ClientService;




@RestController
@RequestMapping("/api/clients")
public class ClientController {


    private final ClientService service;

    public ClientController(ClientService service){
        this.service = service;
    }


    @GetMapping
    public List<ClientDTO> getAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){
        return service.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientCreateDTO dto){
        Client created = service.create(dto);

        ClientDTO response = new ClientDTO(
            created.getId(),
            created.getName(),
            created.getEmail(),
            created.getPhoneNumber()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientCreateDTO dto){
        return service.update(id, dto)
        .map(updated -> {
            ClientDTO response = new ClientDTO(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getPhoneNumber()
            );
            return ResponseEntity.ok(response);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
