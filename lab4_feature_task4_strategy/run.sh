#!/usr/bin/env bash
set -e
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
LANG=C.UTF-8 LC_ALL=C.UTF-8 java -Dfile.encoding=UTF-8 -cp out lab4.app.Main
