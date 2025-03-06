public class ClientesPage extends WebPage {
    public ClientesPage() {
        ListView<Cliente> listView = new ListView<Cliente>("clientes", clienteService.listarTodos()) {
            protected void populateItem(ListItem<Cliente> item) {
                item.add(new Label("nome", item.getModelObject().getNome()));
                item.add(new AjaxLink<Void>("editar") {
                    @Override public void onClick(AjaxRequestTarget target) {
                        // Abrir modal de edição
                    }
                });
            }
        };
        add(listView);
    }
}