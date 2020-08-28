package gallery.service;

import gallery.model.Image;

public interface ImageService {

    Image findById(Integer id);

    void save(Image image);
}
