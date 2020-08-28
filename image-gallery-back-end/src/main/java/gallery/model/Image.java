package gallery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "images", schema = "public")
public class Image {

    @Id
    @GeneratedValue
    private Integer id;
    private String url;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "image", targetEntity = Tag.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tag> tags;

    public Image() {
        tags = new ArrayList<>();
    }

    public void addTag(Tag tag) {
        List<Tag> tags = getTags();
        tags.add(tag);
        setTags(tags);
    }
}
