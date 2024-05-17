package com.be.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.be.dao.ProductImpl;
import com.be.entities.Product;
import com.be.entities.Result;
import com.be.helpers.HandleFile;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.core.header.FormDataContentDisposition;

@Path("product")
public class ProductService {
	ProductImpl productImpl = new ProductImpl();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProduct() {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(productImpl.getAll());
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductById(@PathParam("id") int id) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(productImpl.getById(id));
	}
	
	@GET
	@Path("/search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String searchProductByName(@PathParam("name") String name) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(productImpl.searchByName(name));
	}
	
	@GET
	@Path("/pagination/{page}/{size}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String searchProductByName(@PathParam("page") int page, @PathParam("size") int size, @QueryParam("name") String name) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(productImpl.pagination(page, size, name));
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
	public String insertProduct(
			@FormDataParam("product") String newData, 
			@FormDataParam("file") InputStream uploadedInputStream, 
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@Context ServletContext servletContext
	) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		String thumbnail = null;
		boolean checkUploadFile = true;
		if(fileDetail != null) {
			String fileName = fileDetail.getFileName();
			String pathImages = servletContext.getRealPath("images");
			if(!fileName.isEmpty()) {
				thumbnail = "images/".concat(fileName);
				checkUploadFile = HandleFile.writeToFile(uploadedInputStream, pathImages.concat("\\").concat(fileName));
			} else checkUploadFile = false;
		}
		if(!checkUploadFile) return gson.toJson(new Result(500, "File download error!"));
		Product product = gson.fromJson(newData, Product.class);
		product.setThumbnail(thumbnail);
		Result result = productImpl.insert(product);
		return gson.toJson(result);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
	public String insertProduct(
			@PathParam("id") int id,
			@FormDataParam("product") String newData, 
			@FormDataParam("file") InputStream uploadedInputStream, 
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@Context ServletContext servletContext
			) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		String thumbnail = null;
		boolean checkUploadFile = true;
		
		Product product = productImpl.getById(id);
		if(product == null) return gson.toJson(new Result(402, "Id does not exits!"));
		
		if(fileDetail != null) {
			String fileName = fileDetail.getFileName();
			String pathImages = servletContext.getRealPath("images");
			if(!fileName.isEmpty()) {
				thumbnail = "images/".concat(fileName);
				checkUploadFile = HandleFile.writeToFile(uploadedInputStream, pathImages.concat("\\").concat(fileName));
			} else checkUploadFile = false;
		}
		
		if(!checkUploadFile) return gson.toJson(new Result(500, "File download error!"));
		Product productNew = gson.fromJson(newData, Product.class);
		if(productNew.getCategory_id() != null) product.setCategory_id(productNew.getCategory_id());
		if(productNew.getName() != null) product.setName(productNew.getName());
		if(productNew.getPrice() != null) product.setPrice(productNew.getPrice());
		if(productNew.getDescription() != null) product.setDescription(productNew.getDescription());
		if(productNew.getCreated_at() != null) product.setCreated_at(productNew.getCreated_at());
		if(thumbnail != null && checkUploadFile) product.setThumbnail(thumbnail);
		Result result = productImpl.update(product);
		return gson.toJson(result);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(@PathParam("id") int id) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Result result = productImpl.delete(id);
		return gson.toJson(result);
	}
	
}
