package beans;

import java.io.Serializable;

/**
 * Created by Hristiyan on 20.5.2018 ã..
 */
public class Brand {

    private int id;
    private String brandName;

    public Brand() {

    }

    public Brand(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}
