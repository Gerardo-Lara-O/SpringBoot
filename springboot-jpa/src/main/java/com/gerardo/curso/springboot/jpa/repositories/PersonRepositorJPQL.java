package com.gerardo.curso.springboot.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gerardo.curso.springboot.jpa.dto.PersonDto;
import com.gerardo.curso.springboot.jpa.entities.Person;

public interface PersonRepositorJPQL extends CrudRepository<Person, Long> {

        // Usando JPQL
    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select concat(p.name, ' ', p.lastname ) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    // Obtener todo el arreglo del objeto
    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonaDataList();

    // obtener un solo parametro
   @Query("select p from Person p where p.id=?1")
    Optional<Person> obtenerPersonaDataById(Long id);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();
    
    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    // Usando DTO
    @Query("select new com.gerardo.curso.springboot.jpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDTO();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();

    // @Query("select concat(p.name, ' ', p.lastname ) from Person p")
    // List<String> findFullNameConcat();

    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findFullNameConcat();

    @Query("select UPPER(p.name || ' ' || p.lastname) from Person p")
    List<String> findFullNameConcatUpper();

    @Query("select LOWER(p.name || ' ' || p.lastname) from Person p")
    List<String> findFullNameConcatLower();

    @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
    List<Object[]> findAllPersonDataListCase();


    @Query("select p from Person p where p.id between 2 and 5")
    List<Person> findAllBetweenId();

    @Query("select p from Person p where p.name between 'J' and 'P'")
    List<Person> findAllBetweenName();
}
