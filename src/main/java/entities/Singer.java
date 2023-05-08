package entities;




import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "singer", schema = "public")
@NamedQueries({@NamedQuery(name = Singer.FIND_ALL, query = "select s from Singer s"),
@NamedQuery(name = Singer.FIND_ALL_WITH_ALBUM, query = "select distinct s from Singer s left join fetch s.albums a " +
		"left join fetch s.instruments k"), @NamedQuery(name=Singer.FIND_SINGER_BY_ID,
		query="select distinct s from Singer s " +
				"left join fetch s.albums a " +
				"left join fetch s.instruments i " +
				"where s.id = :id")})
@SqlResultSetMapping(name = "singerResult", entities = @EntityResult(entityClass = Singer.class))
public class Singer extends AbstractEntity implements Serializable {

//	public static final String FIND_SINGER_BY_ID = "Singer.findById";
	public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";
	public static final String FIND_ALL = "Singer.findAll";
	public static final String FIND_SINGER_BY_ID ="Singer.findById";

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@OneToMany(mappedBy = "singer", cascade= CascadeType.ALL,
			orphanRemoval=true, fetch = FetchType.EAGER)
	private Set<Album> albums = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "SINGER_ID"),
			inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
	private Set<Instrument> instruments = new HashSet<>();


	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}


	public Set<Album> getAlbums() {
		return albums;
	}

	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean addAlbum(Album album) {
		album.setSinger(this);
		return albums.add(album);
	}

	public void removeAlbum(Album album) {
		albums.remove(album);
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}

	public boolean addInstrument(Instrument instrument) {
		return instruments.add(instrument);
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("Singer - id: %d, First name: %s, Last name: %s, Birthday: %s",
				id, firstName, lastName, sdf.format(birthDate));
	}


}
