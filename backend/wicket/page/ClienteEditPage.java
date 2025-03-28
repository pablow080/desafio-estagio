import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.model.Model;
import org.apache.wicket.markup.html.form.Button;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.service.ClienteService;

public class ClienteEditPage extends WebPage {

    @SpringBean
    private ClienteService clienteService;

    private Cliente cliente;

    public ClienteEditPage(Long clienteId) {
        cliente = clienteService.buscarPorId(clienteId);

        // Formulário de edição
        Form<Cliente> form = new Form<>("form", new PropertyModel<>(this, "cliente"));
        TextField<String> nomeField = new TextField<>("nome", new PropertyModel<>(cliente, "nome"));
        TextField<String> cpfCnpjField = new TextField<>("cpfCnpj", new PropertyModel<>(cliente, "cpfCnpj"));
        form.add(nomeField);
        form.add(cpfCnpjField);

        form.add(new Button("saveButton") {
            @Override
            public void onSubmit() {
                clienteService.salvar(cliente);
                setResponsePage(new ClientelistPage()); // Redireciona após salvar
            }
        });

        form.add(new Button("cancelButton") {
            @Override
            public void onSubmit() {
                setResponsePage(new ClientelistPage()); // Cancela e retorna para a lista
            }
        });

        add(form);
    }
}
