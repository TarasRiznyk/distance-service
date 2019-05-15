package com.distance.distanceservice.configuration;

import com.distance.distanceservice.service.impl.CitiesGraphServiceImpl;
import com.distance.distanceservice.service.impl.DistanceCalculationService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;
import java.util.Set;

@Configuration
@EnableSwagger2
public class Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DistanceCalculationService getDistanceCalculatorBean(Object... args) {
        return new DistanceCalculationService((Map<String, Set<CitiesGraphServiceImpl.Neighbour>>) args[0]);
    }
}
