package gallery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags", schema = "public")
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @JsonBackReference
    @ManyToOne(targetEntity = Image.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
}
