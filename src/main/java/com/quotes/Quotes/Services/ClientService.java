package com.quotes.Quotes.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quotes.Quotes.DTO.ClientCreateDTO;
import com.quotes.Quotes.DTO.ClientDTO;
import com.quotes.Quotes.Model.Client;
import com.quotes.Quotes.Repository.ClientRepository;

import jakarta.transaction.Transactional;

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

    public Client create(ClientCreateDTO dto){
        Client client = new Client();
        client.setName(dto.name());
        client.setEmail(dto.email());
        client.setPhoneNumber(dto.phoneNumber());

        return repository.save(client);
    }

    @Transactional
    public Optional<Client> update(Long id, ClientCreateDTO newData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(newData.name());
                    existing.setEmail(newData.email());
                    existing.setPhoneNumber(newData.phoneNumber());
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
