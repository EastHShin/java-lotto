package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import java.util.List;

import domain.Rank;

public class WinningLottery {

	final Lottery winningNumbers;
	final LotteryNumber bonusBall;

	private WinningLottery(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
		this.winningNumbers = Lottery.from(winningNumbers);
		validateBonusBall(winningNumbers, bonusBall);
		this.bonusBall = bonusBall;
	}

	public static WinningLottery of(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
		return new WinningLottery(winningNumbers, bonusBall);
	}

	private void validateBonusBall(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
		validateDuplicatedNumber(winningNumbers, bonusBall);
	}

	private void validateDuplicatedNumber(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
		if(winningNumbers.stream()
			.anyMatch(winningNumber -> winningNumber.equals(bonusBall))) {
			throw new IllegalArgumentException(DUPLICATE_NUMBER_EXCEPTION.getMessage());
		}
	}

	public Rank getRank(final Lottery lottery) {
		final int winningCount = winningNumbers.countSameNumber(lottery);
		return calculateRank(winningCount, lottery);
	}

	private Rank calculateRank(final int winningCount, final Lottery lottery) {
		return Rank.getRank(winningCount, lottery.contains(this.bonusBall));
	}
}
