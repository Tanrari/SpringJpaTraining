package entities;
import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "album")
public class Album extends AbstractEntity implements Serializable {

	@Column
	private String title;

	@Temporal(TemporalType.DATE)
	@Column(name = "RELEASE_DATE")

	private Date releaseDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SINGER_ID")
	private Singer singer;

	public Singer getSinger() {
		return this.singer;
	}

	public String getTitle() {
		return this.title;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("Album - id: %d, Singer id: %d, Title: %s, Release Date: %s",
				id, singer.getId(), title, sdf.format(releaseDate));
	}


}
