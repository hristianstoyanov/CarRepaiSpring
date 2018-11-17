package webServices;

import beans.Article;
import beans.Brand;
import beans.Category;
import beans.Model;
import services.DataOperations;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Hristiyan on 19.5.2018 �..
 */

@Path("/")
public class CarRepairEndpoint {

    private DataOperations operations = new DataOperations();

    @GET
    @Path("/brands")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Brand> getAllBrands() {
        return operations.getBrands();
    }

    @GET
    @Path("/models")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Model> getAllModels(@QueryParam("brandId") int brandId) {
        return operations.getModels(brandId);
    }

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategories() {
        return operations.getCategories();
    }

    @GET
    @Path("/articles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticles(@QueryParam("brandId")int brandId, @QueryParam("modelId")int modelId, @QueryParam("categoryId")int categoryId) {
        return operations.getArticlesTitle(brandId, modelId, categoryId);
    }


    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public int uploadImage(@FormParam("encodedImage") String encodedImage,@FormParam("fileName")String fileName) {
        System.out.println("---> " + encodedImage);
        int imageId = -1;
        imageId = operations.uploadImage(encodedImage, fileName);
        return imageId;
    }

    @GET
    @Path("/downloadImage")
    @Produces("image/png")
    public Response downloadImage(@QueryParam("id") int id) throws IOException {
        ByteArrayInputStream byteArrayInputStream = operations.downloadImage(id);
        return Response.ok(byteArrayInputStream).build();
    }

    @GET
    @Path("/categoryId")
    @Produces(MediaType.TEXT_PLAIN)
    public int categoryId(@QueryParam("category") String category) {
        return operations.categoryId(category);
    }

    @GET
    @Path("/articleById")
    @Produces(MediaType.APPLICATION_JSON)
    public Article articleId(@QueryParam("id") int id) {
        return operations.getArticle(id);
    }

    @POST
    @Path("/article")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addArticle(Article article) {
        boolean response = false;
        response = operations.addArticle(article);
        if (response) {
            return Response.status(200).entity("Upload success!").build();
        } else {
            return Response.status(500).entity("Internal server error!").build();
        }
    }

}
