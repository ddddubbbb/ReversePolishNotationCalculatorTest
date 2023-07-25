import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationCalculatorTest {

    ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        int a = reversePolishNotationCalculator.calculatePolishNotation("2 100 +");
        assertEquals(102, a);
    }

    @Test
    public void shouldCalculateSubtraction() {
        int a = reversePolishNotationCalculator.calculatePolishNotation("2 100 -");
        assertEquals(-98, a);
    }

    @Test
    public void shouldCalculateMultiplication() {
        int a = reversePolishNotationCalculator.calculatePolishNotation("2 100 *");
        assertEquals(200, a);
    }

    @Test
    public void shouldRemoveSpaceCharacter() {
        int a = reversePolishNotationCalculator.calculatePolishNotation(" 2   100  *           ");
        assertEquals(200, a);
    }

    @Test
    public void shouldProcessingNegativeNumber() {
        int a = reversePolishNotationCalculator.calculatePolishNotation("-2 -100 -");
        assertEquals(98, a);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}