package test.java.org.desafioestagio.wicketfrontend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HomePageTest {

    private WicketTester wicketTester;

    @BeforeEach
    public void setUp() {
        // Inicializa o WicketTester antes de cada teste
        wicketTester = new WicketTester();
    }

    @Test
    public void testHomePageRendering() {
        // Simula a navegação para a HomePage
        wicketTester.startPage(HomePage.class);

        // Verifica se a página renderiza corretamente
        wicketTester.assertRenderedPage(HomePage.class);

        // Exemplo de assert simples para verificar o conteúdo da página
        wicketTester.assertLabel("labelId", "Texto esperado");
    }
}
