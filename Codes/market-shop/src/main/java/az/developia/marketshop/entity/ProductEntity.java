package az.developia.marketshop.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "VARCHAR(80)", unique = true)
	private String name;

	@Column(columnDefinition = "VARCHAR(6)")
	private String cost;

	@Column(columnDefinition = "VARCHAR(6)")
	private String price;

	@Column(columnDefinition = "VARCHAR(6)")
	private String amount;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date releaseDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date expirationDate;

	@Column(columnDefinition = "VARCHAR(30)")
	private String category;

	public Boolean validate() { // <-- create validation method for prevent from null issue
		return this.id == null;
	}
}
