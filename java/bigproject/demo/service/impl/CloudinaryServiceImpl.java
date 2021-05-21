package bigproject.demo.service.impl;

import bigproject.demo.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {


    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        return this.cloudinary
                .uploader()
                .upload(file, Collections.emptyMap())
                .get(URL)
                .toString();
    }
}


/* //todo add student s cloudinary

 @Override
  public void addStudent(StudentServiceModel studentServiceModel) throws IOException {
    MultipartFile img = studentServiceModel.getImg();
    String imageUrl = cloudinaryService.uploadImage(img);

    StudentEntity studentEntity = new StudentEntity().
        setName(studentServiceModel.getName()).
        setImgUrl(imageUrl);

    studentRepository.save(studentEntity);
  }


 */
