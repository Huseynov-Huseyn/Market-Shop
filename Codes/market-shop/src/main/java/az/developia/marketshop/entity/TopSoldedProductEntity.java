package az.developia.marketshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "top-solded-product")
@Setter
@Getter
public class TopSoldedProductEntity {
	@Id
	@Column(columnDefinition = "VARCHAR(80)")
	private String name;

	@Column(columnDefinition = "VARCHAR(6)")
	private String cost;

	@Column(columnDefinition = "VARCHAR(6)")
	private String price;

	@Column(columnDefinition = "VARCHAR(6)")
	private Integer amount;

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
