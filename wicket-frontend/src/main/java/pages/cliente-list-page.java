public class ClienteListPage extends WebPage {
    public ClienteListPage() {
        add(new FeedbackPanel("feedback"));
        ListView<Cliente> clientes = new ListView<Cliente>("clientes", clienteService.listarTodos()) {
            @Override
            protected void populateItem(ListItem<Cliente> item) {
                Cliente cliente = item.getModelObject();
                item.add(new Label("nome", cliente.getNome()));
                item.add(new Link("editar") {
                    @Override
                    public void onClick() {
                        setResponsePage(new ClienteFormPage(cliente));
                    }
                });
            }
        };
        add(clientes);
    }
}