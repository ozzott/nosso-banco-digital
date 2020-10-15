package com.zup.nossobancodigital.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CadastroSegundaEtapa {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String cep;

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String rua;

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String bairro;

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String complemento;

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String cidade;

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String estado;

    public CadastroSegundaEtapa(@NotNull @NotBlank(message = "Campo obrigatório") String cep, @NotNull @NotBlank(message = "Campo obrigatório") String rua, @NotNull @NotBlank(message = "Campo obrigatório") String bairro, @NotNull @NotBlank(message = "Campo obrigatório") String complemento, @NotNull @NotBlank(message = "Campo obrigatório") String cidade, @NotNull @NotBlank(message = "Campo obrigatório") String estado) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
