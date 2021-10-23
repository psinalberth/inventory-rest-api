package com.github.psinalberth.domain.shared.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {

}
