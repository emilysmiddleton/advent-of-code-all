# Advent of code

Layout is:
resources: input goes here (make sure it is git ignored)
src/esm/aoc:
 - one package for each year
 - one package for each day within each year
 - utils for input parsing under .input
 - data structures under .structures
 - other helper methods under.utils

Each day should have a Java class in the format MainY<2 digit year>D<2 digit day with leading 0>
