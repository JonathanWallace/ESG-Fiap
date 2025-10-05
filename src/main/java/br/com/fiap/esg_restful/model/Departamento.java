package br.com.fiap.esg_restful.model;

//import jakarta.persistence.*;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
//@Table(name = "tbl_departamento")
@Document(collection = "departamentos")
public class Departamento {
    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "seq_tbl_departamento"
//    )
//    @SequenceGenerator(
//            name = "seq_tbl_departamento",
//            sequenceName = "seq_tbl_departamento",
//            allocationSize = 1
//    )
//    @Column(name = "id_departamento")
    private String id;
//
//    @Column(name = "nome_departamento")
    private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
