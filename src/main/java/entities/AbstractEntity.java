package entities;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	protected Long id;
	@Version
	@Column(name = "VERSION")
	private int version;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


}
