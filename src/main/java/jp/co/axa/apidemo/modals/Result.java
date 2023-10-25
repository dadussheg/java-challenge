package jp.co.axa.apidemo.modals;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Result {

	private Object result;

	private String message;

	private Boolean isError;

}
