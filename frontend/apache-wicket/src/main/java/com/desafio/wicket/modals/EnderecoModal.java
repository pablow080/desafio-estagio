public class EnderecoModal extends Panel {
    public EnderecoModal(String id, Endereco endereco) {
        super(id);
        Form<Endereco> form = new Form<>("form", new CompoundPropertyModel<>(endereco));
        form.add(new RequiredTextField<>("cep"));
        form.add(new Button("salvar", new Model<>("Salvar")));
        add(form);
    }
}