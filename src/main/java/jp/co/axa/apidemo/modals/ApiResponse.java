package jp.co.axa.apidemo.modals;

import org.immutables.value.Value;

@Value.Immutable
public interface ApiResponse {

    Object data();

    String status();

    String message();
}
