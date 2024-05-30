package az.developia.marketshop.entity;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "solded-product")
@Setter
@Getter
@ToString
public class SoldedProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "VARCHAR(80)")
	private String name;

	@Column(columnDefinition = "VARCHAR(6)")
	private String cost;

	@Column(columnDefinition = "VARCHAR(6)")
	private String price;

	@Column(columnDefinition = "VARCHAR(6)")
	private Integer amount;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date releaseDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date expirationDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDate soldedDate;

	@Column(columnDefinition = "VARCHAR(30)")
	private String category;

	@Column(name = "barcod", nullable = false)
	private Long barcod;

	public void setBarcod(Long barcod) {
		if (barcod != null && (barcod < 10000000000L || barcod > 99999999999L)) {
			throw new IllegalArgumentException("Barcod must be an 11-digit number.");
		}
		this.barcod = barcod;
	}
}
