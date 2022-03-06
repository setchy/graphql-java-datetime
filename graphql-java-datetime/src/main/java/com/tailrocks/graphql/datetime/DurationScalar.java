/*
 * Copyright 2021 Alexey Zhokhov
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
package com.tailrocks.graphql.datetime;

import graphql.PublicApi;
import graphql.schema.GraphQLScalarType;

@PublicApi
public final class DurationScalar {

    private DurationScalar() {
    }

    public static GraphQLScalarType create(String name) {
        return GraphQLScalarType.newScalar()
                .name(name != null ? name : "Duration")
                .description("A Java 8 ISO 8601 Duration")
                .coercing(new GraphqlDurationCoercing())
                .build();
    }

}