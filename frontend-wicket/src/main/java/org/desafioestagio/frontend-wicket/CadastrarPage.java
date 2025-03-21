import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;

public class CadastrarPage extends WebPage {
    private String nome;
    private String cpfCnpj;

    public CadastrarPage() {
        // Formulário de cadastro
        Form<?> form = new Form<Void>("form");

        // Campos do formulário
        form.add(new TextField<String>("nome", Model.of(nome)));
        form.add(new TextField<String>("cpfCnpj", Model.of(cpfCnpj)));

        // Botão de submissão
        form.add(new AjaxButton("submit", form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                // Chamar o método para enviar os dados para o backend
                salvarCliente();
                // Você pode adicionar um feedback aqui para o usuário
            }
        });

        add(form);
    }

    private void salvarCliente() {
        // Simulação de envio via AJAX (exemplo simplificado)
        WebRequest request = (WebRequest) getRequest();

        String url = "http://localhost:8080/api/clientes";  // URL do backend

        // Aqui você pode usar uma biblioteca de HTTP como RestTemplate, HttpClient, ou um AJAX diretamente
        // RestTemplate é uma das opções para enviar uma requisição POST para o backend
        try {
            // Exemplo de uso de RestTemplate para envio de dados
            RestTemplate restTemplate = new RestTemplate();
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCpfCnpj(cpfCnpj);
            // Enviar os dados via POST
            restTemplate.postForObject(url, cliente, Cliente.class);
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar erro no frontend
        }
    }
}
