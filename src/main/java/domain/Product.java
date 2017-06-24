package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;

@SuppressWarnings("restriction")
@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name="product.all", query="SELECT p FROM Product p WHERE p.name LIKE :name AND p.price >= :priceMin AND p.price <= :priceMax AND p.category = :category"),
	@NamedQuery(name="product.allCat", query="SELECT p FROM Product p WHERE p.name LIKE :name AND p.price >= :priceMin AND p.price <= :priceMax"),
	@NamedQuery(name="product.id", query="FROM Product p WHERE p.id=:productId")
})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Category category;
	private Float price;
	
	//@XmlTransient
	//@OneToMany(mappedBy="person")
	//private List<Car> cars = new ArrayList<Car>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	//public List<Car> getCars() {
	//	return cars;
	//}
	//public void setCars(List<Car> cars) {
	//	this.cars = cars;
	//}
	
}
