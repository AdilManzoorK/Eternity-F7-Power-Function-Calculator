# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),  
and this project adheres to [Semantic Versioning](https://semver.org/).

---

## [3.1.0] - 2025-08-10
### Added
- Integrated **Checkstyle**, **PMD**, and **SpotBugs** analysis with screenshots for D3 requirements.
- Implemented **GUI accessibility features**:
    - Mnemonics for labels and buttons.
    - `labelFor` property for text fields.
    - `accessibleText` descriptions for screen readers.
    - Set default and cancel buttons for improved keyboard navigation.
- Used **JDB / IntelliJ Remote Attach** for debugging:
    - Verified all edge cases (`0^0`, negative base with integer exponent, negative base with real exponent, large values, fractional exponents).
- Added **unit tests** for:
    - Positive and negative integers.
    - Positive and negative real exponents.
    - Edge cases and domain/range errors.
- Created **mind map** for Java programming style selection.

### Fixed
- Adjusted floating-point precision tolerance in real exponent path to match expected results.
- Minor refactoring to conform to **Google Java Style Guide**.

---

## [3.0.0] - 2025-08-03
### Added
- Initial implementation of `pow(x, y)` function:
    - Integer exponents via exponentiation-by-squaring.
    - Real exponents via series expansion of `ln(x)` and `exp(t)`.
    - Domain and range checks for invalid inputs.
- Developed **JavaFX GUI** for user interaction.
- Added **Semantic Versioning** to project (`pom.xml` version updated to 3.0.0).
- Set up **Maven** project with `javafx-maven-plugin`.

---

## [2.0.0] - 2025-07-28
### Added
- Prototype implementation of `pow(x, y)` for integer exponents.
- Basic command-line interface for testing.
- Initial project structure and package setup.

---

## [1.0.0] - 2025-07-21
### Added
- Project initialization.
- Configured Maven build and dependencies.
- Added placeholder classes and documentation for F7 function.
