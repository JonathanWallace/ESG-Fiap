package br.com.fiap.esg_restful.model;
//
//import jakarta.persistence.*;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
//@Table(name = "tbl_cargo")
@Document(collection = "cargos")
public class Cargo {
    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "seq_tbl_cargo"
//    )
//    @SequenceGenerator(
//            name = "seq_tbl_cargo",
//            sequenceName = "seq_tbl_cargo",
//            allocationSize = 1
//    )
//    @Column(name = "id_cargo")
    private String id;

//    @Column(name = "nome_cargo")
    private String nome;

//    @Column(name = "nivel_cargo")
    private String nivel;

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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(id, cargo.id) && Objects.equals(nome, cargo.nome) && Objects.equals(nivel, cargo.nivel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nivel);
    }
}
