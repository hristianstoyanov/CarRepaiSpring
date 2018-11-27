package com.forum.webServices;

import com.forum.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.forum.services.DataOperations;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Hristiyan on 19.5.2018 �..
 */

@RequestMapping("/")
@RestController
public class CarRepairEndpoint {

//    private DataOperations operations = new DataOperations();
    @Autowired
    private DataOperations operations;

//    @GET
//    @Path("/brands")
//    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public List<Brand> getAllBrands() {
        return operations.getBrands();
    }

//    @GET
//    @Path("/models")
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping("/models")
    public List<Model> getAllModels(@RequestParam("brandId") int brandId) {
        return operations.getModels(brandId);
    }

//    @GET
//    @Path("/categories")
//    @Produces(MediaType.APPLICATION_JSON)
//    @RequestMapping("/categories")
//    public List<Category> getAllCategories() {
//        return operations.getCategories();
//    }

//    @GET
//    @Path("/articles")
//    @Produces(MediaType.APPLICATION_JSON)
//    @RequestMapping("/articles")
//    public List<Article> getAllArticles(@RequestParam("brandId")int brandId, @RequestParam("modelId")int modelId, @RequestParam("categoryId")int categoryId) {
//        return operations.getArticlesTitle(brandId, modelId, categoryId);
//    }


//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public int uploadImage(MultipartHttpServletRequest request) {
//      //  System.out.println("---> " + encodedImage);
//        int imageId = -1;
//        Iterator<String> itr =  request.getFileNames();
//
//        MultipartFile mpf = request.getFile(itr.next());
//        try {
//            byte[] imageBytes = mpf.getBytes();
//            String fileName = mpf.getOriginalFilename();
//            imageId = operations.uploadImage(imageBytes, fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return imageId;
//    }

//    @GET
//    @Path("/downloadImage")
//    @Produces("image/png")
//    @RequestMapping(value = "/downloadImage", method = RequestMethod.GET)
//    public ResponseEntity downloadImage(@RequestParam("id") int id) throws IOException {
//        ByteArrayInputStream byteArrayInputStream = operations.downloadImage(id);
//        return ResponseEntity.ok(byteArrayInputStream);
//    }

//    @GET
//    @Path("/categoryId")
//    @Produces(MediaType.TEXT_PLAIN)
//    @RequestMapping(value = "/categoryId", method = RequestMethod.GET)
//    public int categoryId(@RequestParam("category") String category) {
//        return operations.categoryId(category);
//    }

//    @GET
//    @Path("/articleById")
//    @Produces(MediaType.APPLICATION_JSON)
//    @RequestMapping(value = "/articleById", method = RequestMethod.GET)
//    public Article articleId(@RequestParam("id") int id) {
//        return operations.getArticle(id);
//    }

//    @POST
//    @Path("/article")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_PLAIN)
//    @RequestMapping(value = "/article", method = RequestMethod.GET)
//    public ResponseEntity addArticle(Article article) {
//        boolean response = false;
//        response = operations.addArticle(article);
//        if (response) {
//            return ResponseEntity.ok("Upload success!");
//        } else {
//            return ResponseEntity.ok("Internal server error!");
//        }
//    }

}
