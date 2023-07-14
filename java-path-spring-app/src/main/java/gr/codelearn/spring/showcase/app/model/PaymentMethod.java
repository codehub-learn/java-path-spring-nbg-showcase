package gr.codelearn.spring.showcase.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMethod {
	WIRE_TRANSFER(0.10f), CREDIT_CARD(0.15f);
	private final float discount;
}
