package com.forum.webServices;

import com.forum.services.DataOperations;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;


@RequestMapping("/images")
@RestController
public class ImagesEndpoint {

    @Autowired
    private DataOperations operations;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public int uploadImage(@RequestParam("file") MultipartFile request) {
        //  System.out.println("---> " + encodedImage);
        int imageId = -1;
        try {
            byte[] imageBytes = request.getBytes();
            String fileName = request.getOriginalFilename();
            imageId = operations.uploadImage(imageBytes, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageId;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadImage(@RequestParam("id") int id) throws IOException {
        String imageBase64String = operations.downloadImage(id);
        return imageBase64String;
    }
}
