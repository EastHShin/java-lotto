package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.generatestrategy.LotteryNumberGeneratorStrategy;
import domain.lottery.LotteryGenerator;
import domain.lottery.Lotteries;
import domain.lottery.Lottery;
import domain.lottery.LotteryNumber;
import domain.lottery.WinningLottery;

public final class LotteryGame {

	public static final int LOTTERY_PRICE = 1000;

	private final NumOfLottery numOfLottery;
	private final LotteryGenerator lotteryGenerator;
	private final LotteryNumberGeneratorStrategy lotteryNumberGenerator;

	private LotteryGame(final NumOfLottery numOfLottery, final LotteryGenerator lotteryGenerator,
		final LotteryNumberGeneratorStrategy lotteryNumberGenerator) {
		this.numOfLottery = numOfLottery;
		this.lotteryGenerator = lotteryGenerator;
		this.lotteryNumberGenerator = lotteryNumberGenerator;
	}

	public static LotteryGame of(final int inputMoney, final int numOfManualLottery, final LotteryGenerator lotteryGenerator,
		LotteryNumberGeneratorStrategy lotteryNumberGenerator) {
		final NumOfLottery numOfLottery = getNumOfLottery(inputMoney, numOfManualLottery);
		return new LotteryGame(numOfLottery, lotteryGenerator, lotteryNumberGenerator);
	}

	private static NumOfLottery getNumOfLottery(final int inputMoney, final int numOfManualLottery) {
		final Money money = new Money(inputMoney);
		final int numOfTotalLottery = money.divideBy(LOTTERY_PRICE);
		return NumOfLottery.of(numOfTotalLottery, numOfManualLottery);
	}

	public NumOfLottery getTheNumberOfLottery() {
		return this.numOfLottery;
	}

	public Lotteries createLottery(final List<List<Integer>> manualLotteryNumber) {
		final List<Lottery> totalLotteries = generateTotalLotteries(manualLotteryNumber);
		return Lotteries.from(totalLotteries);
	}

	private List<Lottery> generateTotalLotteries(List<List<Integer>> manualLotteryNumber) {
		final List<Lottery> manualLotteries = createManualLottery(manualLotteryNumber);
		final List<Lottery> autoLotteries = createAutoLotteriesNumber();
		return Stream.concat(manualLotteries.stream(), autoLotteries.stream())
			.collect(Collectors.toList());
	}

	private List<Lottery> createManualLottery(List<List<Integer>> manualLotteryNumber) {
		return manualLotteryNumber.stream()
			.map(lotteryGenerator::generateLottery)
			.collect(Collectors.toList());
	}

	private List<Lottery> createAutoLotteriesNumber() {
		final List<Lottery> lotteriesNumber = new ArrayList<>();
		for (int i = 0; i < numOfLottery.getNumOfAutoLottery(); i++) {
			lotteriesNumber.add(lotteryGenerator.generateLottery(lotteryNumberGenerator.generateNumbers()));
		}
		return Collections.unmodifiableList(lotteriesNumber);
	}

	public WinningLottery createWinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		final Lottery lotteryNumbers = lotteryGenerator.generateLottery(winningNumbers);
		final LotteryNumber bonusLotteryBall = new LotteryNumber(bonusBall);
		return WinningLottery.of(lotteryNumbers, bonusLotteryBall);
	}
}
