#!/usr/bin/env bash
ABSOLUTE_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
cd "${ABSOLUTE_PATH}" || exit

appVersion="$(grep 'projectVersion=' gradle.properties | grep -E -v "^[[:blank:]]*//")"
appVersion="${appVersion#*=}"
appVersion="${appVersion//[[:blank:]\'\"]/}"

echo "${appVersion}"
