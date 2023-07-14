package gr.codelearn.spring.showcase.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerCategory {
	INDIVIDUAL(0), BUSINESS(0.2f), GOVERNMENT(0.5f);

	private final float discount;
}
