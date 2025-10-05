package br.com.fiap.esg_restful.repository;

import br.com.fiap.esg_restful.model.TreinamentoColaborador;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinamentoColaboradorRepository extends MongoRepository<TreinamentoColaborador, String> {
}
