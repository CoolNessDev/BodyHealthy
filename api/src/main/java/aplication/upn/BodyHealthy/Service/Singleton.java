package aplication.upn.BodyHealthy.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class Singleton {
    private static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "coolness",
            "api_key", "477874249684767",
            "api_secret", "JHAOpRfo8D-iN1WwzvrR_-6hNM0"));

    public static Cloudinary getCloudinary() {
        return cloudinary;
    }

}
