package br.com.charlesscarvalhoo.controllers;

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

    private PersonServices services;

    public PersonController(PersonServices services){
        this.services = services;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person, UriComponentsBuilder uriBuilder){
        Person created = services.create(person);
        URI uri = uriBuilder.path("/person/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findById(@PathVariable("id") Long id){
        Person founded = services.findById(id);
        return ResponseEntity.ok(founded);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> findAll(){
        List<Person> founded = services.findAll();
        return ResponseEntity.ok(founded);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> update(@RequestBody Person person){
        Person updated = services.update(person);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
