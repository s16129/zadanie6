package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Category;
import domain.Product;
// import domain.Person;
// import domain.services.PersonService;

@Path("/products")
@Stateless
public class ProductResources {
	
	@PersistenceContext
	EntityManager em;
	
	// Wszystkie produkty
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll(
			@DefaultValue("0") @QueryParam("price_min") float priceMin,
	        @DefaultValue("0") @QueryParam("price_max") float priceMax,
	        @QueryParam("name") String name,
	        @QueryParam("category") String category) {
		
		String stmt = "SELECT p FROM Product p";
		List<String> qrs = new ArrayList<String>();
		
		if(category != null){
			qrs.add("p.category = :category");
		}
	
		if(priceMin > 0){
			qrs.add("p.price >= :priceMin");
		}
		
		if(priceMax > 0 && priceMax >= priceMin){
			qrs.add("p.price <= :priceMax");
		}
		
		if(name != null){
			qrs.add("p.name LIKE :name");
		}
		
		if(qrs.size() > 0){
			stmt = stmt + " WHERE ";
			for(String s : qrs){
				stmt = stmt + s + " AND ";
			}
			stmt = stmt.substring(0, stmt.length()-4);
		}
		
		Query q = em.createQuery(stmt);
		
		if(category != null){
			q.setParameter("category", Category.valueOf(category));
		}
	
		if(priceMin > 0){
			q.setParameter("priceMin", priceMin);
		}
		
		if(priceMax > 0 && priceMax >= priceMin){
			q.setParameter("priceMax", priceMax);
		}
		
		if(name != null){
			q.setParameter("name", "%" + name + "%");
		}
		
		return q.getResultList();
	}
	
	// Dodaj produkt
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Product product) {
		em.persist(product);
		return Response.ok(product.getId()).build();
	}
	
	// Pojedynczy produkt
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
		
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	
	// Aktualizuj produkt
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product p){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		result.setName(p.getName());
		result.setCategory(p.getCategory());
		result.setPrice(p.getPrice());
		em.persist(result);
		
		//p.setId(id);
		//db.update(p);
		return Response.ok().build();
	}
	
	// Usun produkt
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("personId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		em.remove(result);
		return Response.ok().build();
	}
	
	/*
	@GET
	@Path("/{personId}/cars")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> getCars(@PathParam("personId") int personId){
		Person result = em.createNamedQuery("personId", Person.class)
				.setParameter("personId", personId)
				.getSingleResult();
		
		if(result==null)
			return null;
		return result.getCars();
	}
	
	@POST
	@Path("/{id}/cars")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCar(@PathParam("id") int personId, Car car){
		Person result = em.createNamedQuery("person.id", Person.class)
				.setParameter("personId", personId)
				.getSingleResult();
		if(result==null)
			return Response.status(404).build();
		
		result.getCars().add(car);
		car.setPerson(result);
		em.persist(car);
		//if(result.getCars()==null)
		//	result.setCars(new ArrayList<Car>());
		//result.getCars().add(car);
		return Response.ok().build();
	}
	*/
}
