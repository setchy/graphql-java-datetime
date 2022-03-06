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
package com.zhokhov.graphql.datetime.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Divino G. de Menezes Neto
 */
@ConfigurationProperties(prefix = "graphql.datetime.scalars")
public class GraphQLDateTimeProperties {

    private ScalarDefinition date = new ScalarDefinition();
    private ScalarDefinition localDate = new ScalarDefinition();
    private ScalarDefinition localDateTime = new ScalarDefinition();
    private ScalarDefinition localTime = new ScalarDefinition();
    private ScalarDefinition offsetDateTime = new ScalarDefinition();
    private ScalarDefinition yearMonth = new ScalarDefinition();
    private ScalarDefinition duration = new ScalarDefinition();
    private boolean zoneConversionEnabled = false;

    ScalarDefinition getDate() {
        return date;
    }

    public void setDate(ScalarDefinition date) {
        this.date = date;
    }

    ScalarDefinition getLocalDate() {
        return localDate;
    }

    public void setLocalDate(ScalarDefinition localDate) {
        this.localDate = localDate;
    }

    ScalarDefinition getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(ScalarDefinition localDateTime) {
        this.localDateTime = localDateTime;
    }

    ScalarDefinition getLocalTime() {
        return localTime;
    }

    public void setLocalTime(ScalarDefinition localTime) {
        this.localTime = localTime;
    }

    ScalarDefinition getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(ScalarDefinition offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    ScalarDefinition getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(ScalarDefinition yearMonth) {
        this.yearMonth = yearMonth;
    }

    ScalarDefinition getDuration() {
        return duration;
    }

    public void setDuration(ScalarDefinition duration) {
        this.duration = duration;
    }

    public boolean isZoneConversionEnabled() {
        return zoneConversionEnabled;
    }

    public void setZoneConversionEnabled(boolean zoneConversionEnabled) {
        this.zoneConversionEnabled = zoneConversionEnabled;
    }

    public static class ScalarDefinition {

        private String scalarName;

        private String format;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        String getScalarName() {
            return scalarName;
        }

        public void setScalarName(String scalarName) {
            this.scalarName = scalarName;
        }

    }

}
