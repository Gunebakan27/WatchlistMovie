package com.openclassrooms.watchlist.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BadMovieValidator.class)
public @interface BadMovie {
    String message() default "if movie has lower than rating < 6 , then comment should be at least 15 characters";
    Class<?>[]groups()default {};
    Class<? extends Payload>[] payload()default{};

}
