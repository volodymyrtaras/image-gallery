package gallery.service.impl;

import gallery.model.Image;
import gallery.repository.ImageRepository;
import gallery.service.ImageService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultImageService implements ImageService {

    @Setter(onMethod = @__(@Autowired))
    private ImageRepository imageRepository;

    @Override
    public Image findById(Integer id) {

        return imageRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Image image) {

        imageRepository.save(image);
    }
}
