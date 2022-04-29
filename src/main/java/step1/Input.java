package step1;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Input {

    private static final Pattern PATTERN = Pattern.compile("^\\d( [-+*/] \\d)+$");
    private static final String DELIMITER = " ";
    private static final int FIRST_OPERAND_COUNT = 1;
    private static final int OPERATION_BUNDLE_SIZE = 2;

    private final List<String> values;
    private final Index index;

    public Input(String input) {
        this(input, Index.ofStart());
    }

    public Input(String input, Index index) {
        validate(input);
        validatePattern(input);
        this.values = List.of(input.split(DELIMITER));
        this.index = index;
    }

    private void validate(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("널이거나 공백 입력입니다.");
        }
    }

    private void validatePattern(String input) {
        if (!PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자와 연산자로만 구성된 공백으로 문자열이어야합니다.");
        }
    }

    public int getOperationBundleCount() {
        return (this.values.size() - FIRST_OPERAND_COUNT) / OPERATION_BUNDLE_SIZE;
    }

    public int getFirstOperand() {
        int firstOperandIndex = this.index.getFirstOperandIndex();
        return toInt(values.get(firstOperandIndex));
    }

    public Operator getNextOperator() {
        int operatorIndex = this.index.getNextOperatorIndex();
        return Operator.toEnum(values.get(operatorIndex));
    }

    public int getNextOperand() {
        int operandIndex = this.index.getNextOperandIndex();
        return toInt(values.get(operandIndex));
    }

    private int toInt(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Input input = (Input) o;
        return Objects.equals(values, input.values) && Objects.equals(index, input.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, index);
    }
}
