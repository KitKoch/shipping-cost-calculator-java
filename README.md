# Shipping Cost Calculator

Java app that asks the user for a package’s weight and prints the
correct shipping cost based on a simple tier table.

| Weight (lb)  | Cost (USD)  |
|--------------|-------------|
| 0 < w < 1    | 3.50        |
| 1 < w <= 3   | 5.50        |
| 3 < w <= 10  | 8.50        |
| 10 < w <= 20 | 10.50       |

- If weight <= 0 lb  => input is rejected.  
- If weight > 20 lb  => package can’t be shipped.

## Features
- Input validation, reprompts on bad data
- Custom Exception instead of System.exit(0) => logic test friendly
- JUnit 5 with parameterized tests for every weight bracket

## Run
```bash
# Clone
git clone https://github.com/KitKoch/shipping-cost-calculator-java.git
cd shipping-cost-calculator-java

# Build & Run Tests
./gradlew clean test