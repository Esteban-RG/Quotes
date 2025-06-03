package com.quotes.Quotes.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quotes.Quotes.DTO.UnitOfMeasureDTO;
import com.quotes.Quotes.Model.UnitOfMeasure;
import com.quotes.Quotes.Repository.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository repository;

    public UnitOfMeasureService(UnitOfMeasureRepository repository){
        this.repository = repository;
    }

   
    public List<UnitOfMeasureDTO> getAll(){
        return repository.findAll()
        .stream()
        .map(unit -> new UnitOfMeasureDTO(unit.getId(), unit.getName()))
        .collect(Collectors.toList());
    }

    public Optional<UnitOfMeasureDTO> getById(Long id){
        return repository.findById(id)
        .map(unit -> new UnitOfMeasureDTO(unit.getId(), unit.getName()));
    }

    public UnitOfMeasure create(UnitOfMeasure unitOfMeasure){
        return repository.save(unitOfMeasure);
    }

    public Optional<UnitOfMeasure> update(Long id, UnitOfMeasure newData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(newData.getName());
                    return repository.save(existing);
                });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(unitOfMeasure -> {
            repository.delete(unitOfMeasure);
            return true;
        }).orElse(false);
    }
}
