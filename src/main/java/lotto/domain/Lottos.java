package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottoList() {
        return lottos;
    }

    public WinningResult checkLottoPrize(List<Integer> winningNums) {
        int matchingCount;
        Integer[] prizeNums = new Integer[Prize.values().length];
        Arrays.fill(prizeNums, 0);

        for (Lotto lotto : lottos) {
            matchingCount = lotto.countMatchingNums(winningNums);

            checkPrize(prizeNums, matchingCount);
        }

        return new WinningResult(Arrays.asList(prizeNums));
    }

    private void checkPrize(Integer[] prizeNums, int matchingCount) {
        try {
            prizeNums[Prize.valueOf(matchingCount).ordinal()]++;
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }

}
