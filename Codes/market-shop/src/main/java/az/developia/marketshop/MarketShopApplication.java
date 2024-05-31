package az.developia.marketshop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class MarketShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketShopApplication.class, args);

		System.out.println("--------------");
		System.out.println("Bazar olsun !");
		System.out.println("--------------");
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper obj = new ModelMapper();
		return obj;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
}
