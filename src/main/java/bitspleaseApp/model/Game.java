package bitspleaseApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "games")
@Table(name = "games")
public class Game implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(length = 80, nullable = false)
    public String name;

    @Column(length = 30)
    public String system;

    @Column(length = 80)
    public String developer;

    @Column(nullable = false)
    public long uploader_id;

    @Column(nullable = false)
    public String uploader_name;

    @Column(nullable = false)
    public BigDecimal price;

    @Column(length = 250000)
    public String image;

    public Game() {

    }

    public Game(String name, String system, long uploader_id, String uploader_name, BigDecimal price) {
        this.name = name;
        this.system = system;
        this.uploader_id = uploader_id;
        this.uploader_name = uploader_name;
        this.price = price;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public long getUploader_id() {
        return uploader_id;
    }

    public void setUploader_id(long uploader_id) {
        this.uploader_id = uploader_id;
    }

    public String getUploader_name() {return uploader_name;}

    public void setUploader_name(String uploader_name) {this.uploader_name = uploader_name;}

    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal price) {this.price = price;}
    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}

//    extra comment

}
