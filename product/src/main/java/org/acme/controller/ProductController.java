package org.acme.controller;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.ProductDTO;
import org.acme.service.ProductService;

import java.util.List;

@Path("/api/products")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findAllProducts(){
        return productService.getAllProducts();
    }

    @POST
    @Transactional
    public Response saveProduct(ProductDTO productDTO){
        try{
            productService.createNewProduct(productDTO);
            return Response.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response changeProduct(@PathParam("id") Long id, ProductDTO productDTO){
        try{
            productService.changeProduct(id, productDTO);
            return Response.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response removeProduct(@PathParam("id") Long id){
        try{
            productService.deleteProduct(id);
            return Response.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
