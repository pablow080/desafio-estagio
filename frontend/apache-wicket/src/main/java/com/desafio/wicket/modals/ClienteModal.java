public class ClienteModal extends Panel {
    public ClienteModal(String id, Cliente cliente) {
        super(id);
        Form<Cliente> form = new Form<>("form", new CompoundPropertyModel<>(cliente));
        form.add(new RequiredTextField<>("nome"));
        form.add(new Button("salvar", new Model<>("Salvar")));
        add(form);
    }
}