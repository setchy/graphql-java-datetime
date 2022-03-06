/*
 * Copyright 2017 Alexey Zhokhov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tailrocks.graphql.datetime.boot.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.autoconfigure.tools.GraphQLJavaToolsAutoConfiguration;
import graphql.schema.GraphQLScalarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author Alexey Zhokhov
 */
public class ContextHelper {

    static public AbstractApplicationContext load() {
        AbstractApplicationContext context;

        try {
            context = AnnotationConfigApplicationContext.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        AnnotationConfigRegistry registry = (AnnotationConfigRegistry) context;

        registry.register(BaseConfiguration.class);
        registry.register(GraphQLJavaToolsAutoConfiguration.class);

        context.refresh();

        return context;
    }

    @Configuration
    @ComponentScan("com.tailrocks.graphql.datetime.boot")
    static class BaseConfiguration {

        // initialize date time types here
        @Autowired(required = false) GraphQLScalarType graphQLDate;
        @Autowired(required = false) GraphQLScalarType graphQLLocalDate;
        @Autowired(required = false) GraphQLScalarType graphQLLocalDateTime;
        @Autowired(required = false) GraphQLScalarType graphQLLocalTime;
        @Autowired(required = false) GraphQLScalarType graphQLOffsetDateTime;

        @Bean
        public ObjectMapper objectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

            return mapper;
        }

    }

}
