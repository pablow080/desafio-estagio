ModalWindow modal = new ModalWindow("modal");
modal.setContent(new ClienteForm(modal.getContentId(), modal));
add(modal);

PageableListView<Cliente> clientesList = new PageableListView<>("clientes", clienteService.listarTodos(), 10);

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
        form.add(new RequiredTextField<>("cpfCnpj").add(new CPFValidator()));
    }
}