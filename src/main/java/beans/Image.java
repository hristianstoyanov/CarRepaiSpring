package beans;

/**
 * Created by Hristiyan on 26.5.2018 ã..
 */
public class Image {

    private String name;
    private String encodedData;

    public Image() {

    }

    public Image(String name, String encodedData) {
        this.name = name;
        this.encodedData = encodedData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncodedData() {
        return encodedData;
    }

    public void setEncodedData(String encodedData) {
        this.encodedData = encodedData;
    }

}
