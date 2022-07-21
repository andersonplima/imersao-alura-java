import java.util.stream.Stream;

public abstract class BaseContentReader {
    private final String imageUrlFieldName;
    private final String titleFieldName;

    protected BaseContentReader(String titleFieldName, String imageUrlFieldName) {
        this.titleFieldName = titleFieldName;
        this.imageUrlFieldName = imageUrlFieldName;
    }

    public Stream<Content> parseContentList(String json) {
        var parser = new JsonParser();
        var contentMap = parser.parse(json);
        return contentMap.stream().map(map -> {
            var title = map.get(titleFieldName);
            var imageUrl = map.get(imageUrlFieldName);
            return new Content(title, imageUrl);
        });
    }
}
