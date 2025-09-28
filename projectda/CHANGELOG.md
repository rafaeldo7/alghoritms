# Changelog

All notable changes to this project will be documented in this file.

## [1.0.0] - 2025-09-28

### ✨ Features
* **(cli)** Added a command-line interface to run algorithms and emit results to a CSV file.
* **(closest)** Implemented the closest pair of points algorithm using a divide and conquer approach.
* **(select)** Implemented the deterministic select algorithm (Median of Medians).
* **(quicksort)** Implemented QuickSort with a randomized pivot and recursion on the smaller partition.
* **(mergesort)** Implemented MergeSort with a reusable buffer and a cutoff for small arrays.
* **(metrics)** Added a metrics system including counters, a recursion depth tracker, and a CSV writer.

### 🐛 Bug Fixes
* **(fix)** Addressed edge cases, including handling of duplicates and tiny arrays.

### 🚀 Performance
* **(bench)** Added a JMH harness to benchmark the performance of `select` vs `sort`.

### 🛠️ Refactoring
* **(util)** Refactored common functions like `partition`, `swap`, and `shuffle` into utility classes.

### 📚 Documentation
* **(report)** Added a report to `readme.md` with analysis of Master Theorem cases, Akra-Bazzi intuition, and initial plots.

### Initial Commit
* **(init)** Initialized the project with Maven, JUnit 5, and a basic `readme.md`.
