package root.demo.entites;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class UserDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userId;

	@Column(name = "FIRST_NAME")
	private String fName;

	@Column(name = "LAST_NAME")
	private String lName;

	@Column(name = "CITY")
	private String city;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "REVIEWER", nullable = false)
	private Boolean reviewer;
	
	public UserDetails() {
	}

	public UserDetails(Long userId, String fName, String lName, String city, String country, String email,
			Boolean reviewer) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.city = city;
		this.country = country;
		this.email = email;
		this.reviewer = reviewer;
	}







	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetails userDetails = (UserDetails) o;
		return Objects.equals(userId, userDetails.userId) && Objects.equals(fName, userDetails.fName)
				&& Objects.equals(lName, userDetails.lName) && Objects.equals(city, userDetails.city)
				&& Objects.equals(country, userDetails.country) && Objects.equals(email, userDetails.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, fName, lName, city, country, email);
	}

	@Override
	public String toString() {
		return "UserDetails{" + "userId=" + userId + ", fName='" + fName + '\'' + ", lName='" + lName + '\''
				+ ", city='" + city + '\'' + ", country='" + country + '\'' + ", email='" + email + '\'' + '}';
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getReviewer() {
		return reviewer;
	}

	public void setReviewer(Boolean reviewer) {
		this.reviewer = reviewer;
	}

	
	
}
