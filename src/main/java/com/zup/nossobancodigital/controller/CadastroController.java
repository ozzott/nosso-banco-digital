package com.zup.nossobancodigital.controller;

import com.zup.nossobancodigital.models.Cliente;
import com.zup.nossobancodigital.request.CadastroPrimeiraEtapa;
import com.zup.nossobancodigital.request.CadastroSegundaEtapa;
import com.zup.nossobancodigital.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    private final ClienteService clienteService;

    @Autowired
    public CadastroController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/etapa-1")
    public ResponseEntity <Cliente> cadastroClienteEtapa1(@Valid @RequestBody CadastroPrimeiraEtapa cadastroPrimeiraEtapa, HttpServletRequest httpServletRequest) {

        Cliente cliente = clienteService.cadastroPrimeiraEtapa(cadastroPrimeiraEtapa);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromServletMapping(httpServletRequest).path("/cadastro/etapa-2/" + cliente.getId()).build().expand(cliente).toUri();
        headers.setLocation(location);
        return new ResponseEntity(cliente, headers, HttpStatus.CREATED);
    }

    @PostMapping("/etapa-2/{id}")
    public ResponseEntity <String> cadastroClienteEtapa2(@PathVariable(name = "id") int id, HttpServletRequest httpServletRequest,
                                                         @Valid @RequestBody CadastroSegundaEtapa cadastroSegundaEtapa) {

        cadastroSegundaEtapa.setId(id);
        Cliente cliente = clienteService.cadastroSegundaEtapa(cadastroSegundaEtapa);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromServletMapping(httpServletRequest).path("/cadastro/etapa-3/" + id).build().expand().toUri();
        headers.setLocation(location);
        return new ResponseEntity(cliente, headers, HttpStatus.CREATED);
    }
}
