package com.forum.webServices;

import com.forum.services.DataOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Hrisi on 25.11.2018 ã..
 */
@RequestMapping("/images")
public class ImagesEndpoint {

    @Autowired
    private DataOperations operations;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public int uploadImage(MultipartHttpServletRequest request) {
        //  System.out.println("---> " + encodedImage);
        int imageId = -1;
        Iterator<String> itr =  request.getFileNames();

        MultipartFile mpf = request.getFile(itr.next());
        try {
            byte[] imageBytes = mpf.getBytes();
            String fileName = mpf.getOriginalFilename();
            imageId = operations.uploadImage(imageBytes, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageId;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity downloadImage(@RequestParam("id") int id) throws IOException {
        ByteArrayInputStream byteArrayInputStream = operations.downloadImage(id);
        return ResponseEntity.ok(byteArrayInputStream);
    }
}
