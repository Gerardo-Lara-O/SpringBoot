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


    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name asc, p.lastname desc")
    List<Person> findAllBetweenId(Long c1, Long c2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc")
    List<Person> findAllBetweenName(String c1, String c2);

    // Usando nomenclatura
    List<Person> findByIdBetweenOrderByIdDesc(Long id1, Long id2);
    List<Person> findByNameBetweenOrderByNameDescLastnameAsc(String name1, String name2);

    @Query("select p from Person p order by p.name , p.lastname desc")
    List<Person> getAllOrdered();

    List<Person> findAllByOrderByNameDesc();


    // Funciones JPQL
    // count()
    @Query("select count(p) from Person p")
    Long getTotalPerson();

    // min() max()
    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    // length
    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> getPersonNameLength();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();

    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunction();

    // subconsultas
    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    public List<Object[]> getShorterName();

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();

    @Query("select p from Person p where p.id in ?1")
    public List<Person> getPersonsByIds(List<Long> ids);
}
