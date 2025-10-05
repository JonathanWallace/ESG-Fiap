package br.com.fiap.esg_restful.repository;

import br.com.fiap.esg_restful.model.IndicadoresDepartamento;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.query.Procedure;

public interface IndicadoresDepartamentoRepository extends MongoRepository<IndicadoresDepartamento, String> {

//    @Procedure(procedureName = "PRC_ATUALIZA_INDICADORES_DEPARTAMENTO")
//    void atualizarIndicadoresDepartamento();
}
