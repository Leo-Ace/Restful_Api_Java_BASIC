package com.fe.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fe.entities.Category;
import com.fe.entities.Paginate;
import com.fe.entities.Product;
import com.fe.entities.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String uri="http://localhost:8080/be/api";
    /**
     * Default constructor. 
     */
    public HomeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		request.setCharacterEncoding("UTF-8");
		String vjsp = "index.jsp";
		String action = request.getParameter("action");
		String q = request.getParameter("q");
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		
		Client client = Client.create();
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		if(q != null) {
			WebResource resource = client.resource(uri).path("product/pagination").path(String.valueOf(page)).path("5").queryParam("name", q);
			String data = resource.get(String.class);
			Paginate<Product> pagination = gson.fromJson(data, Paginate.class);
			request.setAttribute("pagination", pagination);
		} else if(action == null) {
			WebResource resource = client.resource(uri).path("product/pagination").path(String.valueOf(page)).path("5");
			String data = resource.get(String.class);
			Paginate<Product> pagination = gson.fromJson(data, Paginate.class);
			request.setAttribute("pagination", pagination);
		} else if(action.equals("add")) {
			WebResource resource = client.resource(uri).path("category");
			String data = resource.get(String.class);
			TypeToken<List<Category>> typeListCategory = new TypeToken<List<Category>>() {};
			List<Category> listCategory = gson.fromJson(data, typeListCategory.getType());
			request.setAttribute("listCategory", listCategory);
			vjsp = "add.jsp";
		} else if(action.equals("edit")) {
			String id = request.getParameter("id");
			WebResource resource1 = client.resource(uri).path("product").path(id);
			String data1 = resource1.get(String.class);
			Product product = gson.fromJson(data1, Product.class);
			request.setAttribute("product", product);
			
			WebResource resource2 = client.resource(uri).path("category");
			String data2 = resource2.get(String.class);
			TypeToken<List<Category>> typeListCategory = new TypeToken<List<Category>>() {};
			List<Category> listCategory = gson.fromJson(data2, typeListCategory.getType());
			request.setAttribute("listCategory", listCategory);
			
			vjsp = "edit.jsp";
		} else if(action.equals("onadd") || action.equals("onedit")) {
			FormDataMultiPart formData = new FormDataMultiPart();
			Integer id = null;
			Integer category_id = null;
			String name = null;
			Float price = null;
			String description = null;
			String thumbnail = null;
			try {
	            List<FileItem> items = upload.parseRequest(request);
	            for (FileItem item : items) {
	                if (!item.isFormField()) {
	                	InputStream fileInputStream = item.getInputStream();
	                    FormDataContentDisposition contentDisposition = FormDataContentDisposition.name(item.getFieldName())
	                        .fileName(item.getName()).build();
	                    if(contentDisposition.getFileName() != null && !contentDisposition.getFileName().isEmpty()) {
	                    	FormDataBodyPart filePart = new FormDataBodyPart(contentDisposition, fileInputStream, MediaType.APPLICATION_OCTET_STREAM_TYPE);
	                    	formData.bodyPart(filePart);	                    	
	                    }
	                } else {
	                    String fieldName = item.getFieldName();
	                    if (fieldName.equals("name")) {
	                        name = item.getString("UTF-8");
	                    } else if (fieldName.equals("category_id")) {
	                    	category_id = Integer.parseInt(item.getString("UTF-8"));
	                    } else if (fieldName.equals("price")) {
	                    	price = Float.parseFloat(item.getString("UTF-8"));
	                    } else if (fieldName.equals("description")) {
	                    	description = item.getString("UTF-8");
	                    } else if (fieldName.equals("id")) {
	                    	id = Integer.parseInt(item.getString("UTF-8"));
	                    } else if (fieldName.equals("thumbnail")) {
	                    	thumbnail = item.getString("UTF-8");
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			ClientResponse res = null;
			Product product = new Product(
					category_id, name, price, description, "", new Date()
					);
			if(action.equals("onadd")) {
				WebResource resource = client.resource(uri).path("product");
				String productData = gson.toJson(product);
				formData.field("product", productData, MediaType.APPLICATION_JSON_TYPE);
				res = resource.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, formData);				
			} else {
				product.setId(id);
				product.setThumbnail(thumbnail);
				WebResource resource = client.resource(uri).path("product").path(String.valueOf(id));
				String productData = gson.toJson(product);
				formData.field("product", productData, MediaType.APPLICATION_JSON_TYPE);
				res = resource.type(MediaType.MULTIPART_FORM_DATA).put(ClientResponse.class, formData);				
			}
			Result responseData = gson.fromJson(res.getEntity(String.class), Result.class);
			request.setAttribute("responseData", responseData);
			WebResource resource = client.resource(uri).path("product/pagination").path(String.valueOf(page)).path("5");
			String data = resource.get(String.class);
			Paginate<Product> pagination = gson.fromJson(data, Paginate.class);
			request.setAttribute("pagination", pagination);
		} else if(action.equals("delete")) {
			WebResource resource = client.resource(uri).path("product").path(request.getParameter("id"));
			ClientResponse res = resource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
			Result responseData = gson.fromJson(res.getEntity(String.class), Result.class);
			request.setAttribute("responseData", responseData);
			resource = client.resource(uri).path("product/pagination").path(String.valueOf(page)).path("5");
			String data = resource.get(String.class);
			Paginate<Product> pagination = gson.fromJson(data, Paginate.class);
			request.setAttribute("pagination", pagination);
		}
		
		request.getRequestDispatcher(vjsp).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
