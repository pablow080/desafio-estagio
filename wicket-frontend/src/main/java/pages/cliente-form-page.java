public class ClienteFormPage extends WebPage {
    public ClienteFormPage(Cliente cliente) {
        Form<Cliente> form = new Form<>("form", new CompoundPropertyModel<>(cliente));
        form.add(new TextField<>("nome"));
        form.add(new AjaxButton("salvar") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                clienteService.save(cliente);
                target.add(getFeedback());
                setResponsePage(ClienteListPage.class);
            }
        };
        add(form);
    }
}