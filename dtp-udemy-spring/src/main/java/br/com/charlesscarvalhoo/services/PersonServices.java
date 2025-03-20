package br.com.charlesscarvalhoo.services;

import br.com.charlesscarvalhoo.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){

        logger.info("Finding All People ");

        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }


    public Person findById(String id){

        logger.info("Finding a Person !");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Charles");
        person.setLastName("Carvalho");
        person.setAddress("Rua 1");
        person.setGender("Masculino");

        return person;
    }

    public Person create(Person person){

        logger.info("Creating a Person");

        return person;
    }


    public void delete(String id){

        logger.info("Deleting a Person");

    }

    public Person update(Person person){

        logger.info("Updating a Person");
        return person;
    }


    private Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First Name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("Some Address in Brazil");
        person.setGender("Male");

        return person;
    }

}
