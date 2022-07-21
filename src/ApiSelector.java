import java.lang.reflect.InvocationTargetException;

public enum ApiSelector {
    NASA ("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-10", 
        NasaContentReader.class),
    NEKOS ("https://nekos.best/api/v2/kitsune?amount=5", NekosContentReader.class);
    private final String url;
    public String getUrl() {
        return url;
    }    
    private final Class<? extends BaseContentReader> contentReaderType;
    public BaseContentReader createContentReaderInstance() {
        try {
            return contentReaderType.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
    ApiSelector(String url, Class<? extends BaseContentReader> contentReaderType) {
        this.contentReaderType = contentReaderType;
        this.url = url;
    }
}
