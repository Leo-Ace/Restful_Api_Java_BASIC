package com.be.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.be.dao.CategoryImpl;
import com.be.entities.Category;
import com.be.entities.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("category")
public class CategoryService {
	CategoryImpl categoryImpl = new CategoryImpl();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCategory() {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(categoryImpl.getAll());
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getCategoryById(@PathParam("id") int id) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(categoryImpl.getById(id));
	}
	
	@GET
	@Path("/search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String searchCategoryByName(@PathParam("name") String name) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(categoryImpl.searchByName(name));
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertCategory(String newData) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Category category = gson.fromJson(newData, Category.class);
		Result result = categoryImpl.insert(category);
		return gson.toJson(result);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateCategory(@PathParam("id") int id, String newData) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Category category = categoryImpl.getById(id);
		if(category == null) return gson.toJson(new Result(402, "Id does not exist!"));
		Category newCate = gson.fromJson(newData, Category.class);
		if(newCate.getName() != null) category.setName(newCate.getName());
		if(newCate.getStatus() != null) category.setStatus(newCate.getStatus());
		if(newCate.getCreated_at() != null) category.setCreated_at(newCate.getCreated_at());
		Result result = categoryImpl.update(category);
		return gson.toJson(result);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertCategory(@PathParam("id") int id) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Result result = categoryImpl.delete(id);
		return gson.toJson(result);
	}
}
