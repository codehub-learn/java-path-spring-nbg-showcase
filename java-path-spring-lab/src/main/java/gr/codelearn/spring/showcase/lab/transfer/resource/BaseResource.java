package gr.codelearn.spring.showcase.lab.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
public class BaseResource implements Serializable {
	protected Long id;
}
