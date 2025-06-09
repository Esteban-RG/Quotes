package com.quotes.Quotes.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quotes.Quotes.DTO.UnitOfMeasure.UnitOfMeasureCreateDTO;
import com.quotes.Quotes.DTO.UnitOfMeasure.UnitOfMeasureDTO;
import com.quotes.Quotes.Model.UnitOfMeasure;
import com.quotes.Quotes.Repository.UnitOfMeasureRepository;

import jakarta.transaction.Transactional;

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

    public UnitOfMeasure create(UnitOfMeasureCreateDTO dto){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setName(dto.name());

        return repository.save(unitOfMeasure);
    }

    @Transactional
    public Optional<UnitOfMeasure> update(Long id, UnitOfMeasureCreateDTO newData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(newData.name());
                    return repository.save(existing);
                });
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<UnitOfMeasure> optionalUnit = repository.findById(id);

        if (optionalUnit.isEmpty()) {
            return false; 
        }

        UnitOfMeasure unit = optionalUnit.get();

        if (!unit.getProducts().isEmpty()) {
            return false;
        }

        repository.delete(unit);
        return true;
    }
}
