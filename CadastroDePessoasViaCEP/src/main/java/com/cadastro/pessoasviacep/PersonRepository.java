package com.cadastro.pessoasviacep;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.pessoasviacep.controller.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
    // Você pode adicionar métodos de consulta personalizados, se necessário
}


