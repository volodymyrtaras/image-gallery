package gallery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", targetEntity = Image.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

    public User() {
        images = new ArrayList<>();
    }

    public void addImage(Image image) {
        List<Image> images = getImages();
        images.add(image);
        setImages(images);
    }
}
