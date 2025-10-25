package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ColaboradorModel {

    @Expose(serialize = false)
    private Long id;

    @Expose
    private String nome;

    @Expose
    private String raca;

    @Expose
    private String genero;

    @Expose
    private String dataNascimento;

    @Expose
    private String dataAdmissao;

    @Expose
    private DepartamentoModel departamento;

    @Expose
    private CargoModel cargo;

    @Expose
    private Double salario;

    @Expose
    private Boolean possuiDeficiencia;

    @Expose
    private String orientacaoSexual;

    public ColaboradorModel() {
        // Inicializa os objetos aninhados para evitar NullPointerException
        this.departamento = new DepartamentoModel();
        this.cargo = new CargoModel();
    }
}