/*
 *  Copyright 2017 Alexey Zhokhov
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.tailrocks.graphql.datetime

import graphql.language.StringValue
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime
import java.util.concurrent.TimeUnit

import static java.time.ZoneOffset.UTC

/**
 * @author Alexey Zhokhov
 */
class GraphQLLocalTimeTest extends Specification {

    def setup() {
        TimeZone.setDefault(TimeZone.getTimeZone(UTC))
    }

    @Unroll
    def "LocalTime parse literal #literal.value as #result"() {
        expect:
            new GraphqlLocalTimeCoercing().parseLiteral(literal) == result

        where:
            literal                     | result
            new StringValue('00:00:00') | LocalTime.MIDNIGHT
            new StringValue('10:15:30') | LocalTime.of(10, 15, 30)
            new StringValue('17:59:59') | LocalTime.of(17, 59, 59)
    }

    @Unroll
    def "LocalTime parseLiteral throws exception for invalid #literal"() {
        when:
            new GraphqlLocalTimeCoercing().parseLiteral(literal)

        then:
            thrown(CoercingParseLiteralException)

        where:
            literal                            | _
            new StringValue('')                | _
            new StringValue('not a localtime') | _
    }

    @Unroll
    def "LocalTime serialize #value into #result (#result.class)"() {
        expect:
            new GraphqlLocalTimeCoercing().serialize(value) == result

        where:
            value                                                              | result
            LocalTime.MIDNIGHT                                                 | '00:00:00'
            LocalTime.of(10, 15, 30)                                           | '10:15:30'
            LocalTime.of(17, 59, 59)                                           | '17:59:59'
            LocalTime.of(17, 59, 59, (int) TimeUnit.MILLISECONDS.toNanos(277)) | '17:59:59.277'
    }

    @Unroll
    def "serialize throws exception for invalid input #value"() {
        when:
            new GraphqlLocalTimeCoercing().serialize(value)
        then:
            thrown(CoercingSerializeException)

        where:
            value             | _
            ''                | _
            'not a localtime' | _
            new Object()      | _
    }

    @Unroll
    def "LocalTime parse #value into #result (#result.class)"() {
        expect:
            new GraphqlLocalTimeCoercing().parseValue(value) == result

        where:
            value          | result
            '00:00:00'     | LocalTime.MIDNIGHT
            '10:15:30'     | LocalTime.of(10, 15, 30)
            '17:59:59'     | LocalTime.of(17, 59, 59)
            '17:59:59.277' | LocalTime.of(17, 59, 59, (int) TimeUnit.MILLISECONDS.toNanos(277))
    }

    @Unroll
    def "parseValue throws exception for invalid input #value"() {
        when:
            new GraphqlLocalTimeCoercing().parseValue(value)
        then:
            thrown(CoercingParseValueException)

        where:
            value             | _
            ''                | _
            'not a localtime' | _
            new Object()      | _
    }

}
