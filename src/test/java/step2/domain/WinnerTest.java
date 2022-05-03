package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName(value = "지난 당첨 번호 테스트")
class WinnerTest {

    @Test
    void 문자열을_입력_받아서_컴마로_구분하여_지난_당첨_번호_객체를_생성() {

        Winner winner = new Winner("1, 2, 3, 4, 5, 6");
        Winner winner2 = new Winner("1, 2, 3, 4, 5, 6");

        assertThat(winner).isEqualTo(winner2);
    }

    @ParameterizedTest(name = "{displayName} -> [{index}] : {arguments}")
    @NullAndEmptySource
    void 주어진_문자열이_널이거나_공백으로만_이루어져있으면_예외(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Winner(input)
        );
    }

    @Test
    void 주어진_문자열의_컴마로_구분된_번호_갯수가_각_회차에_할당된_당첨번호_수와_다르면_예외() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Winner("1, 2")
        );
    }

    @Test
    void 주어진_문자열의_컴마로_구분된_번호에_중복이_존재하면_예외() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Winner("1, 1, 2")
        );
    }

    @ParameterizedTest(name = "{displayName} -> [{index}] : {arguments}")
    @CsvSource(value = {"1:true", "45:false"}, delimiter = ':')
    void 주어진_로또_번호가_지난_당첨_번호에_포함되는지_반환(String input, boolean result) {
        assertThat(new Winner("1, 2, 3, 4, 5, 6").isContain(new LottoNumber(input))).isEqualTo(result);
    }
}