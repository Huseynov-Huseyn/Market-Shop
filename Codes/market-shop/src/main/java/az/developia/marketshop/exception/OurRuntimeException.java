package az.developia.marketshop.exception;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OurRuntimeException extends RuntimeException {

	private BindingResult br;

	public OurRuntimeException(BindingResult br, String m) {
		super(m);
		this.br = br;

	}
}