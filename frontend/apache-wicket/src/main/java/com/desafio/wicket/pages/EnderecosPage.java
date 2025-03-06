ModalWindow modal = new ModalWindow("modal");
modal.setContent(new EnderecoForm(modal.getContentId(), modal));
add(modal);

PageableListView<Endereco> enderecosList = new PageableListView<>("enderecos", clienteService.listarTodos(), 10);


public class EnderecosPage extends WebPage {
    public EnderecosPage() {
        ListView<Endereco> listView = new ListView<Endereco>("enderecos", enderecoService.listarTodos()) {
            protected void populateItem(ListItem<Endereco> item) {
                item.add(new Label("cep", item.getModelObject().getCep()));
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