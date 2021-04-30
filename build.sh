#!/bin/zsh

cd library

javac --release 8 -classpath "/Applications/Processing.app/Contents/Java/core.jar" -d . ../src/palettes/Palettes.java
jar cf palettes.jar palettes ../data

rm -rf palettes
