import java.io.File;
import java.io.FileInputStream;

public class App {
    public static void main(String[] args) throws Exception {
        new StickersGenerator()
            .cria(new FileInputStream(
                new File("entrada/filme.jpg")), 
                "saida/filme.png",
                new String[] {"Tô aqui", "pensando na merda", "que você falou..."} );
    }
}
