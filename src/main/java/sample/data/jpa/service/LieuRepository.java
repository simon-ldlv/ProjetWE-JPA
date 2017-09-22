package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Lieu;
import sample.data.jpa.domain.Personne;

// Imports ...

@Transactional
public interface LieuRepository extends JpaRepository<Lieu, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */  
  public Lieu findByName(String name);


}