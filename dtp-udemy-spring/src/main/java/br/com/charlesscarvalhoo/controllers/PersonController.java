package br.com.charlesscarvalhoo.controllers;

import br.com.charlesscarvalhoo.data.dto.PersonDTO;
import br.com.charlesscarvalhoo.model.Person;
import br.com.charlesscarvalhoo.services.PersonServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonServices services;

    public PersonController(PersonServices services){
        this.services = services;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO PersonDTO, UriComponentsBuilder uriBuilder){
        PersonDTO created = services.create(PersonDTO);
        URI uri = uriBuilder.path("/person/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id){
        PersonDTO founded = services.findById(id);
        return ResponseEntity.ok(founded);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAll(){
        List<PersonDTO> founded = services.findAll();
        return ResponseEntity.ok(founded);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO PersonDTO){
        PersonDTO updated = services.update(PersonDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
