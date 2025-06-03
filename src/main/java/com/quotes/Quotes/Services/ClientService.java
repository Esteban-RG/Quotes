package com.quotes.Quotes.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quotes.Quotes.DTO.ClientDTO;
import com.quotes.Quotes.Model.Client;
import com.quotes.Quotes.Repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository){
        this.repository = repository;
    }

   
    public List<ClientDTO> getAll(){
        return repository.findAll()
        .stream()
        .map(client  -> new ClientDTO(client .getId(), client .getEmail(), client .getName(), client .getPhoneNumber()))
        .collect(Collectors.toList());
    }

    public Optional<ClientDTO> getById(Long id){
        return repository.findById(id)
        .map(client  -> new ClientDTO(client .getId(), client .getEmail(), client .getName(), client .getPhoneNumber()));
    }

    public Client create(Client product){
        return repository.save(product);
    }

    public Optional<Client> update(Long id, Client newData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(newData.getName());
                    return repository.save(existing);
                });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(product -> {
            repository.delete(product);
            return true;
        }).orElse(false);
    }
}
