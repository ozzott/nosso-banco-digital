package com.zup.nossobancodigital.service;

import com.zup.nossobancodigital.models.Cliente;
import com.zup.nossobancodigital.request.CadastroPrimeiraEtapa;
import com.zup.nossobancodigital.request.CadastroSegundaEtapa;

public interface ClienteService {

   Cliente cadastroPrimeiraEtapa(CadastroPrimeiraEtapa cadastroPrimeiraEtapa);

   Cliente cadastroSegundaEtapa(CadastroSegundaEtapa cadastroSegundaEtapa);
}
