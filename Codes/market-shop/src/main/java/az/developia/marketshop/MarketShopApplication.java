package az.developia.marketshop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MarketShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketShopApplication.class, args);

		System.out.println("Hello world!");
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper obj = new ModelMapper();
		return obj;
	}
}
