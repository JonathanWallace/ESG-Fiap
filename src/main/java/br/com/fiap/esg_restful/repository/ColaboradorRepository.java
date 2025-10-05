package br.com.fiap.esg_restful.repository;

import br.com.fiap.esg_restful.model.Colaborador;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends MongoRepository<Colaborador, String> {
}
