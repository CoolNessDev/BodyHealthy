package aplication.upn.BodyHealthy.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {
    Cloudinary cloudinary = Singleton.getCloudinary();

    private Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryService() {
//        valuesMap.put("cloud_name", "coolness");
//        valuesMap.put("api_key", "477874249684767");
//        valuesMap.put("api_secret", "JHAOpRfo8D-iN1WwzvrR_-6hNM0");
//        cloudinary = new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
       /* Map result2 = cloudinary.uploader().upload(file,
                ObjectUtils.asMap(
                        "eager", Arrays.asList(
                                new EagerTransformation().width(400).height(300).crop("pad"),
                                new EagerTransformation().width(260).height(200).crop("crop").gravity("north"))));*/
        file.delete();
        return result;
    }

    public Map delete(String id) throws IOException {
        try {
            Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        } catch (Exception e) {
                System.out.println("Error1: " + e);
            try {
                Map result = cloudinary.uploader().deleteByToken(id);
            } catch (Exception h) {
                System.out.println("Error2: " + h);
            }
        }
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
