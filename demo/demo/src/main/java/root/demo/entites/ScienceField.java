package root.demo.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name="SCIENCE_FIELD")
public class ScienceField {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scienceFieldId;
	
	@Column(name="NAME")
	private String name;

	public Long getScienceFieldId() {
		return scienceFieldId;
	}

	public void setScienceFieldId(Long scienceFieldId) {
		this.scienceFieldId = scienceFieldId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScienceField(Long scienceFieldId, String name) {
		super();
		this.scienceFieldId = scienceFieldId;
		this.name = name;
	}

	public ScienceField() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
