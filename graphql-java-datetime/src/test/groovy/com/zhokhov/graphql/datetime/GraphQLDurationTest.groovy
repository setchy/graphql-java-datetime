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
package com.zhokhov.graphql.datetime

import graphql.language.StringValue
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import spock.lang.Specification
import spock.lang.Unroll

import java.time.Duration

import static java.time.ZoneOffset.UTC

/**
 * @author Alexey Zhokhov
 *
 * Test Java 8 ISO 8601 Duration
 */
class GraphQLDurationTest extends Specification {

    def setup() {
        TimeZone.setDefault(TimeZone.getTimeZone(UTC))
    }

    @Unroll
    def "Duration parse literal #literal.value as #result"() {
        expect:
            new GraphqlDurationCoercing().parseLiteral(literal) == result

        where:
            literal                    | result
            new StringValue('PT1H30M') | Duration.ofMinutes(90)
            new StringValue('P1DT3H')  | Duration.ofHours(27)
    }

    @Unroll
    def "Duration parseLiteral throws exception for invalid #literal"() {
        when:
            new GraphqlDurationCoercing().parseLiteral(literal)

        then:
            thrown(CoercingParseLiteralException)

        where:
            literal                           | _
            new StringValue('')               | _
            new StringValue('not a duration') | _
    }

    @Unroll
    def "Duration serialize #value into #result (#result.class)"() {
        expect:
            new GraphqlDurationCoercing().serialize(value) == result

        where:
            value                | result
            Duration.ofHours(27) | 'PT27H'
    }

    @Unroll
    def "serialize Duration throws exception for invalid input #value"() {
        when:
            new GraphqlDurationCoercing().serialize(value)

        then:
            thrown(CoercingSerializeException)

        where:
            value            | _
            ''               | _
            'not a duration' | _
            '1DT3H'          | _
            new Object()     | _
    }

    @Unroll
    def "Duration parse #value into #result (#result.class)"() {
        expect:
            new GraphqlDurationCoercing().parseValue(value) == result

        where:
            value     | result
            'PT1H30M' | Duration.ofMinutes(90)
            'P1DT3H'  | Duration.ofHours(27)
    }

    @Unroll
    def "Duration parseValue throws exception for invalid input #value"() {
        when:
            new GraphqlDurationCoercing().parseValue(value)

        then:
            thrown(CoercingParseValueException)

        where:
            value        | _
            ''           | _
            'not a date' | _
            '1DT3H'      | _
            new Object() | _
    }

}
