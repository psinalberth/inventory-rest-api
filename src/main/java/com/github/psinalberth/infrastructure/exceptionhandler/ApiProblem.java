package com.github.psinalberth.infrastructure.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiProblem {

    private Integer status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String uri;
    private String userMessage;
    private List<Detail> details;

    @Builder
    @Getter
    public static class Detail {

        private String name;
        private String userMessage;
    }
}
