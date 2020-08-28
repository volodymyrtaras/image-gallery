package gallery.api;

import gallery.model.Image;
import gallery.model.Tag;
import gallery.model.User;
import gallery.service.ImageService;
import gallery.service.TagService;
import gallery.service.UserService;
import gallery.service.impl.DefaultCloudinaryService;
import gallery.session.SessionData;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ImageApi {

    @Setter(onMethod = @__(@Autowired))
    private ImageService imageService;

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;

    @Setter(onMethod = @__(@Autowired))
    private TagService tagService;

    @Setter(onMethod = @__(@Autowired))
    private DefaultCloudinaryService cloudinaryService;

    @PostMapping(value = "/image")
    @CrossOrigin(origins = "http://localhost:4200")
    public Image postImage(@RequestParam("file") MultipartFile file) throws Exception {

        Map<String, String> parameters = cloudinaryService.upload(file.getBytes(), new HashMap<>());

        Image image = new Image();
        User user = userService.findByEmail(SessionData.currentUser.getEmail());

        image.setUser(user);
        image.setUrl(parameters.get("url"));
        imageService.save(image);

        user.addImage(image);
        userService.save(user);

        SessionData.currentImageId = image.getId();

        return image;
    }

    @GetMapping(value = "/image")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Image> getAllImagesForCurrentUser() {

        return userService.getCurrentUser().getImages();
    }

    @GetMapping(value = "/image/current")
    @CrossOrigin(origins = "http://localhost:4200")
    public Integer getCurrentImageId() {

        return SessionData.currentImageId;
    }

    @PostMapping(value = "/image/current/{imageId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void postTagsForImage(@PathVariable String imageId, @RequestBody Image image) {

        Image oldImage = imageService.findById(Integer.valueOf(imageId));
        List<Tag> tags = image.getTags();
        for (Tag tag : tags) {
            tag = tagService.save(tag);
            oldImage.addTag(tag);
        }

        imageService.save(oldImage);

        SessionData.currentImageId = null;
    }
}
