package az.developia.marketshop.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
@Table(name = "product")
@Setter
@Getter
@ToString
public class ProductEntity {
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

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate releaseDate;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate expirationDate;

	@Column(columnDefinition = "VARCHAR(30)")
	private String category;

	@Column(name = "barcod", nullable = false, unique = true)
	private Long barcod;

	public void setBarcod(Long barcod) {
		if (barcod != null && (barcod < 10000000000L || barcod > 99999999999L)) {
			throw new IllegalArgumentException("Barcod must be an 11-digit number.");
		}
		this.barcod = barcod;
	}
}
