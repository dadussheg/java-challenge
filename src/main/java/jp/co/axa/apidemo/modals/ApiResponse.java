package jp.co.axa.apidemo.modals;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse {

    private Object data;

    private String status;

    private String message;
}
