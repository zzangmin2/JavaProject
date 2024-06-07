package daelim.book.rental.admin.util;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class UploadFileService {

    public String upload(MultipartFile file){
        boolean result = false;
        //파일 저장
        String fileOriName = file.getOriginalFilename();
        String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
        System.out.println(fileExtension);
        String uploadDir = "D:\\JavaProject\\BookRental\\src\\main\\webapp\\resources\\upload";
        UUID uuid = UUID.randomUUID();
        String uniqName = uuid.toString().replaceAll("-", "");
        File saveFile = new File(uploadDir + "\\" + uniqName + fileExtension);

        if(!saveFile.exists()){
            saveFile.mkdirs();
        }

        try{
            file.transferTo(saveFile);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        if(result){
            System.out.println("FILE UPLOAD SUCCESS!");
            return uniqName+fileExtension;
        }else{
            System.out.println("FILE UPLOAD FAIL");
            return null;
        }
    }

}
