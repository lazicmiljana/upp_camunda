package root.demo.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAGAZINE")
public class Magazine implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ISSN")
	private Long issn;

	@Column(name = "NAME", length = 127)
	private String name;

	@Column(name = "TYPE")
	private String payingType;

//	@Column(name = "GLAVNI_UREDNIK")
//	private UserDetails chiefEditor;
	
	@Column(name="SCIENCE_FIELD")
	private String scienceField;
	
//	private List<UserDetails> reviewers;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayingType() {
		return payingType;
	}




	public Magazine(Long issn, String name, String payingType, String scienceField) {
		super();
		this.issn = issn;
		this.name = name;
		this.payingType = payingType;
		this.scienceField = scienceField;
	}

	public void setPayingType(String payingType) {
		this.payingType = payingType;
	}

	public Magazine() {
		super();
	}

	public Long getIssn() {
		return issn;
	}

	public void setIssn(Long issn) {
		this.issn = issn;
	}

	public String getScienceField() {
		return scienceField;
	}

	public void setScienceField(String scienceField) {
		this.scienceField = scienceField;
	}

	@Override
	public String toString() {
		return "Magazine [issn=" + issn + ", name=" + name + ", payingType=" + payingType + ", scienceField="
				+ scienceField + "]";
	}
	
}
