package entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instrument" , schema = "public")
public class Instrument  implements Serializable {
	@Id
	@Column(name = "INSTRUMENT_ID")
	private String instrumentId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
			inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
	private Set<Singer> singers = new HashSet<>();

	public String getInstrumentId() {
		return this.instrumentId;
	}

	public Set<Singer> getSingers() {
		return this.singers;
	}

	public void setSingers(Set<Singer> singers) {
		this.singers = singers;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Override
	public String toString() {
		return "Instrument :" + getInstrumentId();
	}
}