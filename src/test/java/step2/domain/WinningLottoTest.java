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
class WinningLottoTest {

    @Test
    void 문자열과_보너스_번호를_입력_받아서_컴마로_구분하여_지난_당첨_번호_객체를_생성() {

        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        WinningLotto winningLotto2 = new WinningLotto("1, 2, 3, 4, 5, 6", "7");

        assertThat(winningLotto).isEqualTo(winningLotto2);
    }

    @ParameterizedTest(name = "{displayName} -> [{index}] : {arguments}")
    @NullAndEmptySource
    void 주어진_보너스_번호가_널이거나_공백이면_예외(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new WinningLotto("1, 2, 3, 4, 5, 6", input)
        );
    }

    @Test
    void 주어진_보너스_번호가_지난_당첨_번호와_중복되면_예외() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new WinningLotto("1, 2, 3, 4, 5, 6", "6")
        );
    }

    @ParameterizedTest(name = "{displayName} -> [{index}] : {0} -> {1}")
    @CsvSource(
            delimiter = ':',
            value = {
                    "1, 2, 3, 4, 5, 6:FIRST",
                    "1, 2, 3, 4, 5, 7:SECOND",
                    "1, 2, 3, 4, 5, 16:THIRD",
                    "1, 2, 3, 4, 15, 16:FOURTH",
                    "1, 2, 3, 4, 15, 7:FOURTH",
                    "1, 2, 3, 14, 15, 16:FIFTH",
                    "1, 2, 3, 14, 15, 7:FIFTH",
                    "1, 2, 13, 14, 15, 16:ETC",
                    "1, 2, 13, 14, 15, 7:ETC",
                    "1, 12, 13, 14, 15, 16:ETC",
                    "1, 12, 13, 14, 15, 7:ETC",
                    "11, 12, 13, 14, 15, 16:ETC",
                    "11, 12, 13, 14, 15, 7:ETC"
            }
    )
    void 주어진_로또_번호의_등수를_반환(String input, LottoRank expect) {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        Lotto lotto = new Lotto(input);

        LottoRank rank = winningLotto.calculateRank(lotto);

        assertThat(rank).isEqualTo(expect);
    }
}