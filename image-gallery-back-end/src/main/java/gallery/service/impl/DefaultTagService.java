package gallery.service.impl;

import gallery.model.Tag;
import gallery.repository.TagRepository;
import gallery.service.TagService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultTagService implements TagService {

    @Setter(onMethod = @__(@Autowired))
    private TagRepository tagRepository;

    @Override
    public Tag save(Tag tag) {

        return tagRepository.save(tag);
    }
}
