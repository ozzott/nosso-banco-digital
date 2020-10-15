package com.zup.nossobancodigital.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CadastroPrimeiraEtapa {

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String sobrenome;

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotNull
    @NotBlank(message = "Campo obrigatório")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public CadastroPrimeiraEtapa() {
    }

    public CadastroPrimeiraEtapa(String nome, String sobrenome, String email, Date dataNascimento, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }
}
