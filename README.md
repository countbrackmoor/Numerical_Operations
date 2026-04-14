# Numerical Operations

This project provides a Java program for converting numbers between different bases (2-25).

## Features

- Converts positive integers from any base (2-25) to another base (2-25)
- Supports hexadecimal digits (a-f for bases > 10)
- Input validation for bases and digit values
- Command-line interface

## Usage

1. Compile the Java file:
   ```
   javac NumericConversions.java
   ```

2. Run the program:
   ```
   java NumericConversions
   ```

3. Follow the prompts to enter:
   - A positive integer (use letters a-f for hexadecimal digits)
   - The base of the input number (2-25)
   - The target base for conversion (2-25)

## Example

```
Please enter a positive integer: 101
Please specify the base of that integer, between 2 and 25: 2
Please specify the base you would like that integer converted to, between 2 and 25: 10
101 converted from base 2 to base 10 is 5_10
```

## Notes

- Input is case-insensitive (a-f or A-F for hexadecimal)
- Program validates that digits are valid for the specified base
- Output includes the original number, bases, and converted result