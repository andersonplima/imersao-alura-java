import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        var api = ApiSelector.NASA;
        String json = HttpRequestGetter
                .get(api.getUrl());
        BaseContentReader nasaContentReader = api.createContentReaderInstance();
        Stream<Content> contentStream = nasaContentReader.parseContentList(json);
        var stickerMaker = new StickerMaker();
        contentStream.forEach(content -> makeSticker(stickerMaker, content));
    }

    private static void makeSticker(StickerMaker stickerMaker, Content content) {
        try {
            stickerMaker.make(new URL(content.imageUrl()).openStream(),
                    "saida/" + content.title() + ".png",
                    new String[] { "I have ", "a bad feeling", "about this..." });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
