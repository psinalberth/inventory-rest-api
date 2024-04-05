package com.github.psinalberth.api.providers.reactor;

import com.github.psinalberth.domain.shared.model.Identity;
import com.github.psinalberth.domain.shared.model.Result;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;

import static com.github.psinalberth.api.providers.reactor.ServerResponseStatusMapper.mapExceptionToStatus;

public class ServerResponseExtensions {

    public static <T extends Identity> Mono<ServerResponse> handleCreatedResponse(
            final ServerRequest request,
            final Result<T> result
    ) {
        return result.isSuccess()?
                makeCreatedResponse(request, result.getValue()) :
                makeErrorResponse(request, result.getException());
    }

    public static <T> Mono<ServerResponse> handleOkResponse(
            final ServerRequest request,
            final Result<T> result
    ) {
        return result.isSuccess()?
                ServerResponse.ok().bodyValue(result.getValue()) :
                makeErrorResponse(request, result.getException());
    }

    public static Mono<ServerResponse> makeCsvSuccessResponse(final InputStream inputStream) {
        return ServerResponse.ok()
                .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", "report.csv"))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .bodyValue(new InputStreamResource(inputStream));
    }

    public static <T extends Identity> Mono<ServerResponse> makeCreatedResponse(
            final ServerRequest request,
            final T body
    ) {
        return ServerResponse
                .created(request.uriBuilder().pathSegment(body.id()).build())
                .bodyValue(body);
    }

    public static Mono<ServerResponse> makeErrorResponse(final ServerRequest request, final Throwable exception) {
        var httpStatus = mapExceptionToStatus(exception);
        return ServerResponse
                .status(httpStatus.value())
                .bodyValue(makeErrorResponseBody(httpStatus, request, exception));
    }

    private static Map<String, Object> makeErrorResponseBody(
            final HttpStatus httpStatus,
            final ServerRequest request,
            final Throwable exception
    ) {
        return Map.of(
                "status", httpStatus.value(),
                "message", exception.getMessage(),
                "path", request.path(),
                "timestamp", LocalDateTime.now()
        );
    }

}
