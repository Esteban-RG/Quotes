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
        .map(cat -> new ClientDTO(cat.getId(), cat.getEmail(), cat.getName(), cat.getPhoneNumber()))
        .collect(Collectors.toList());
    }

    public Optional<ClientDTO> getById(Long id){
        return repository.findById(id)
        .map(cat -> new ClientDTO(cat.getId(), cat.getEmail(), cat.getName(), cat.getPhoneNumber()));
    }

    public Client create(Client category){
        return repository.save(category);
    }

    public Optional<Client> update(Long id, Client newData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(newData.getName());
                    return repository.save(existing);
                });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(category -> {
            repository.delete(category);
            return true;
        }).orElse(false);
    }
}
