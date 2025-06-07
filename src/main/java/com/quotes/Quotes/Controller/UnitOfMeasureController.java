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

import com.quotes.Quotes.DTO.UnitOfMeasureCreateDTO;
import com.quotes.Quotes.DTO.UnitOfMeasureDTO;
import com.quotes.Quotes.Model.UnitOfMeasure;
import com.quotes.Quotes.Services.UnitOfMeasureService;




@RestController
@RequestMapping("/api/units-of-measure")
public class UnitOfMeasureController {


    private final UnitOfMeasureService service;

    public UnitOfMeasureController(UnitOfMeasureService service){
        this.service = service;
    }


    @GetMapping
    public List<UnitOfMeasureDTO> getAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitOfMeasureDTO> getUnitOfMeasureById(@PathVariable Long id){
        return service.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UnitOfMeasureDTO> create(@RequestBody UnitOfMeasureCreateDTO dto){
        UnitOfMeasure created = service.create(dto);

        UnitOfMeasureDTO response = new UnitOfMeasureDTO(
            created.getId(),
            created.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitOfMeasureDTO> update(@PathVariable Long id, @RequestBody UnitOfMeasureCreateDTO dto){
        return service.update(id, dto)
        .map(updated -> {
            UnitOfMeasureDTO response = new UnitOfMeasureDTO(
                updated.getId(),
                updated.getName()
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
