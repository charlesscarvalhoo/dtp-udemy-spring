package br.com.charlesscarvalhoo.services;

import br.com.charlesscarvalhoo.Exceptions.ResourceNotFoundException;
import br.com.charlesscarvalhoo.controllers.TestLogController;
import br.com.charlesscarvalhoo.data.dto.PersonDTO;
import br.com.charlesscarvalhoo.mapper.ObjectMapper;
import br.com.charlesscarvalhoo.model.Person;
import br.com.charlesscarvalhoo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.charlesscarvalhoo.mapper.ObjectMapper.parseListObjects;
import static br.com.charlesscarvalhoo.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    PersonRepository repository;

    public PersonServices(PersonRepository repository){
        this.repository = repository;
    }

    public PersonDTO create(PersonDTO personDTO){

        logger.info("Creating a Person");

        Person person = parseObject(personDTO, Person.class);
        return parseObject(repository.save(person), PersonDTO.class);
    }

    public List<PersonDTO> findAll(){

        logger.info("Finding All People ");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){

        logger.info("Finding a Person !");

        Person person = repository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        return parseObject(person,PersonDTO.class);
    }

    public PersonDTO update(PersonDTO personDTO){

        logger.info("Updating a Person");

        Person person = repository.findById(personDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setAddress(personDTO.getAddress());
        person.setGender(personDTO.getGender());

        return parseObject(repository.save(person), PersonDTO.class);
    }

    public void delete(Long id){

        logger.info("Deleting a Person");

        Person founded = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        repository.delete(founded);
    }
}
