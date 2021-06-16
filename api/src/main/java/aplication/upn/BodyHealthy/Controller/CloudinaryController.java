package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Dto.Message;
import aplication.upn.BodyHealthy.Service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CloudinaryController {
    @Autowired
    CloudinaryService cloudinaryService;


//    @GetMapping("/list")
//    public ResponseEntity<List<String>> list(){
//        List<String> list = imagenService.list();
//        return new ResponseEntity(list, HttpStatus.OK);
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("multipartFile") MultipartFile multipartFile)throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
       List<String> imagen = new ArrayList<>();
       imagen.add((String)result.get("original_filename"));
       imagen.add((String)result.get("url"));
/*        System.out.println(imagen.get(1));*/
       imagen.add((String)result.get("public_id"));

        return new ResponseEntity(new Message(imagen.get(1)+":-:"+imagen.get(2)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id)throws IOException {
//        if(!imagenService.exists(id))
//            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        Imagen imagen = imagenService.getOne(id).get();
        Map result = cloudinaryService.delete(id);
        System.out.println("Result: "+ result);
//        imagenService.delete(id);
        return new ResponseEntity(new Message("imagen eliminada"), HttpStatus.OK);
    }
}
