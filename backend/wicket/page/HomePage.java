import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import wicket.page.ClienteListPage;
import wicket.page.CadastroPage;
import wicket.page.EnderecoListPage;
import wicket.page.RelatoriosPage;

public class HomePage extends WebPage {

    public HomePage() {
        // Título da página
        add(new Label("title", new Model<>("Bem-vindo ao Sistema de Gestão")));

        // Link para página de Cadastro
        add(new Link<Void>("cadastroLink") {
            @Override
            public void onClick() {
                // Redireciona para a página de cadastro
                setResponsePage(CadastroPage.class);
            }
        });

        // Link para página de Lista de Clientes
        add(new Link<Void>("clienteLink") {
            @Override
            public void onClick() {
                // Redireciona para a página de lista de clientes
                setResponsePage(ClientelistPage.class);
            }
        });

        // Link para página de Lista de Endereços
        add(new Link<Void>("enderecoLink") {
            @Override
            public void onClick() {
                // Redireciona para a página de lista de endereços
                setResponsePage(EnderecoListPage.class);
            }
        });

        // Link para geração de relatórios
        add(new Link<Void>("relatoriosLink") {
            @Override
            public void onClick() {
                // Redireciona para a página de relatórios
                setResponsePage(RelatoriosPage.class);
            }
        });
    }
}
