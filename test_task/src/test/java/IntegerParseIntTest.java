import org.assertj.core.api.AssertProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerParseIntTest {

    @Test
    public void testNumberFormatExceptionsFromNullOrEmptyInput() {
        //проверка строки на null
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt(null, 10)
        ).isInstanceOf(NumberFormatException.class);

        //Проверка, что radix не меньше минимального значения (2)
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt("", 1)
        ).isInstanceOf(NumberFormatException.class);

        //Проверка, что radix не превышает максимальное значение (36)
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt("", 37)
        ).isInstanceOf(NumberFormatException.class);

        //Проверка, что длина строки больше 0
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt("", 10)
        ).isInstanceOf(NumberFormatException.class);
    }

    @Test
    public void testNumberFormatExceptionsFromIncorrectInput() {
        //Проверка, что первым символом идёт '-' или '+'
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt(",", 10)
        ).isInstanceOf(NumberFormatException.class);

        //Проверка, что первый символ - '-' или '+', но длина строки больше 1
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt("-", 10)
        ).isInstanceOf(NumberFormatException.class);

        //Проверка, что число не выходит за рамки Integer
        long moreThanIntMaxValue = (long) Integer.MAX_VALUE + 1;
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt(Long.toString(moreThanIntMaxValue), 10)
        ).isInstanceOf(NumberFormatException.class);

        long lessThanIntMinValue = (long) Integer.MIN_VALUE - 1;
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt(Long.toString(lessThanIntMinValue), 10)
        ).isInstanceOf(NumberFormatException.class);

        //Проверка, что цифры в строке не выходят за пределы заданной radix-ом
        // системы счисления
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt("99", 8)
        ).isInstanceOf(NumberFormatException.class);

        //Проверка, что все символы в строке являются цифрами
        Assertions.assertThatThrownBy(
                () -> Integer.parseInt("Test", 10)
        ).isInstanceOf(NumberFormatException.class);
    }

    @Test
    public void testCorrectResults() {
        //Проверка на успешный parse положительных чисел
        Assertions.assertThat(
                Integer.parseInt("123", 10)
                ).isEqualTo(123);

        //Проверка на успешный parse отрицательных чисел
        Assertions.assertThat(
                Integer.parseInt("-123", 10)
        ).isEqualTo(-123);
    }
}
