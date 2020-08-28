package gallery.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class DefaultCloudinaryService {

    final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "dhlyhn67g",
        "api_key", "635775115563456",
        "api_secret", "WTnNlJxvgoyjayqMIj4aKRujw0Y"));

    @SuppressWarnings("rawtypes")
    public Map upload(byte[] file, Map<String, Object> parameters) throws IOException {

        return cloudinary.uploader().upload(file, parameters);
    }
}
