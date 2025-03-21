package org.desafioestagio.frontend-wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.desafioestagio.backend.service.ClienteService;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.desafioestagio.backend.model.Cliente;

public class ClienteExibicaoPage extends WebPage {

    @SpringBean
    private ClienteService clienteService;

    public ClienteExibicaoPage(PageParameters parameters) {
        Long clienteId = parameters.get("id").toLong();
        Cliente cliente = clienteService.findById(clienteId); // Recupere o cliente do banco

        // Exibe os dados do cliente
        add(new Label("nome", Model.of(cliente.getNome())));
        add(new Label("cpfCnpj", Model.of(cliente.getCpfCnpj())));
        add(new Label("email", Model.of(cliente.getEmail())));
        add(new Label("dataNascimento", Model.of(cliente.getDataNascimento().toString())));
        add(new Label("ativo", Model.of(cliente.isAtivo() ? "Ativo" : "Inativo")));
    }
}