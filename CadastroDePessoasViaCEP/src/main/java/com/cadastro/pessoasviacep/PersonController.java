package com.cadastro.pessoasviacep;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cadastro.pessoasviacep.controller.Person;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private RestTemplate restTemplate; // Você pode injetar o RestTemplate como um bean.

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        // Faça a chamada ao serviço ViaCEP para obter informações de endereço com base no CEP fornecido.
        String viaCepUrl = "https://viacep.com.br/ws/" + person.getCep() + "/json/";
        ViaCepResponse viaCepResponse = restTemplate.getForObject(viaCepUrl, ViaCepResponse.class);
        
        if (viaCepResponse != null) {
            // Atualize os campos de endereço da pessoa com os dados do ViaCEP.
            person.setStreet(viaCepResponse.getLogradouro());
            person.setCity(viaCepResponse.getLocalidade());
            person.setState(viaCepResponse.getUf());
            
            // Salve a pessoa no repositório.
            personRepository.save(person);
        }
        
        // Retorne a pessoa criada.
        return person;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        // Recupere a pessoa do repositório por ID.
        Optional<Person> person = personRepository.findById(id);
        
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        // Verifique se a pessoa com o ID fornecido existe no repositório.
        Optional<Person> existingPerson = personRepository.findById(id);
        
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            
            // Atualize os campos da pessoa com os dados fornecidos.
            person.setName(updatedPerson.getName());
            person.setEmail(updatedPerson.getEmail());
            // Atualize outros campos conforme necessário.
            
            // Salve a pessoa atualizada no repositório.
            personRepository.save(person);
            
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        // Verifique se a pessoa com o ID fornecido existe no repositório.
        Optional<Person> existingPerson = personRepository.findById(id);
        
        if (existingPerson.isPresent()) {
            // Exclua a pessoa do repositório por ID.
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Outros métodos do controlador...
}
