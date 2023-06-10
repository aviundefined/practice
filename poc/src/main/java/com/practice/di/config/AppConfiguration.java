package com.practice.di.config;

import io.dropwizard.Configuration;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@NotNull
@Getter
public class AppConfiguration extends Configuration {
}
