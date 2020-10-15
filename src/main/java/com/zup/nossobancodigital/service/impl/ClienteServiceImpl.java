package com.zup.nossobancodigital.service.impl;

import com.zup.nossobancodigital.exception.BadRequestException;
import com.zup.nossobancodigital.exception.ResourceNotFoundException;
import com.zup.nossobancodigital.models.Cliente;
import com.zup.nossobancodigital.repository.ClienteRepository;
import com.zup.nossobancodigital.request.CadastroPrimeiraEtapa;
import com.zup.nossobancodigital.request.CadastroSegundaEtapa;
import com.zup.nossobancodigital.service.ClienteService;
import com.zup.nossobancodigital.utils.ValidacaoUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastroPrimeiraEtapa(CadastroPrimeiraEtapa cadastroPrimeiraEtapa) {

        StringBuilder mensagemErro = new StringBuilder("");

        if(!ValidacaoUtils.isCPF(cadastroPrimeiraEtapa.getCpf())){
            mensagemErro.append("Cpf inválido, ");
        }

        Optional<Cliente> clienteOpt = clienteRepository.findByCpf(cadastroPrimeiraEtapa.getCpf());
        if (clienteOpt.isPresent()) {
            mensagemErro.append("Cpf já existente, ");
        }

        if(!EmailValidator.getInstance().isValid(cadastroPrimeiraEtapa.getEmail())) {
            mensagemErro.append("Formato de e-mail inválido, ");
        }
        clienteOpt = clienteRepository.findByEmail(cadastroPrimeiraEtapa.getEmail());
        if (clienteOpt.isPresent()) {
            mensagemErro.append("E-mail já existente, ");
        }

        if (cadastroPrimeiraEtapa.getDataNascimento().after(new Date())) {
            mensagemErro.append("Data de nascimento maior que a atual, ");
        }

        if(ValidacaoUtils.calculateAge(cadastroPrimeiraEtapa.getDataNascimento()) < 18 ) {
            mensagemErro.append("Menor que 18 anos, ");
        }

        if (mensagemErro.length() > 0) {
            throw new BadRequestException(mensagemErro.toString());
        }

        Cliente cliente = new Cliente (cadastroPrimeiraEtapa.getNome(),cadastroPrimeiraEtapa.getSobrenome(), cadastroPrimeiraEtapa.getEmail(), cadastroPrimeiraEtapa.getDataNascimento(), cadastroPrimeiraEtapa.getCpf());
        return clienteRepository.save(cliente);

    }

    @Override
    public Cliente cadastroSegundaEtapa(CadastroSegundaEtapa cadastroSegundaEtapa) {

        StringBuilder mensagemErro = new StringBuilder("");

        if(!cadastroSegundaEtapa.getCep().matches("\\d{5}-\\d{3}")){
            mensagemErro.append("Formato do Cep inválido, ");
        }

        Cliente cliente = clienteRepository.findById(cadastroSegundaEtapa.getId()).orElseThrow(()-> new ResourceNotFoundException("Cliente", "Id", cadastroSegundaEtapa.getId()));

        if (mensagemErro.length() > 0) {
            throw new BadRequestException(mensagemErro.toString());
        }

        cliente.setCep(cadastroSegundaEtapa.getCep());
        cliente.setRua(cadastroSegundaEtapa.getRua());
        cliente.setBairro(cadastroSegundaEtapa.getBairro());
        cliente.setComplemento(cadastroSegundaEtapa.getComplemento());
        cliente.setCidade(cadastroSegundaEtapa.getCidade());
        cliente.setEstado(cadastroSegundaEtapa.getEstado());

        return clienteRepository.save(cliente);
    }
}