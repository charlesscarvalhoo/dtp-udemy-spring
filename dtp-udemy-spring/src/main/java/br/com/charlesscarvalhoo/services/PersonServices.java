package br.com.charlesscarvalhoo.services;

import br.com.charlesscarvalhoo.Exceptions.ResourceNotFoundException;
import br.com.charlesscarvalhoo.model.Person;
import br.com.charlesscarvalhoo.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    PersonRepository repository;

    public PersonServices(PersonRepository repository){
        this.repository = repository;
    }

    public Person create(Person person){

        logger.info("Creating a Person");

        repository.save(person);

        return person;
    }

    public List<Person> findAll(){

        logger.info("Finding All People ");

        return repository.findAll();
    }

    public Person findById(Long id){

        logger.info("Finding a Person !");

        return repository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    public Person update(Person person){

        logger.info("Updating a Person");

        Person founded = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        founded.setFirstName(person.getFirstName());
        founded.setLastName(person.getLastName());
        founded.setAddress(person.getAddress());
        founded.setGender(person.getGender());

        return repository.save(founded);
    }

    public void delete(Long id){

        logger.info("Deleting a Person");

        Person founded = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        repository.delete(founded);
    }
}
