package view;

import static view.messages.InputExceptionMessages.*;
import static view.messages.InputViewMessages.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
	private static final Pattern WINNING_NUMBER_PATTERN = Pattern.compile("^([1-9]{1,2}[,][\\s]?){5}[1-9]{1,2}$");
	private static final String WINNING_NUMBER_DELIMITER = ",";

	public int inputValidMoney() {
		final String money = inputMoney();
		return Integer.parseInt(money);
	}

	private String inputMoney() {
		System.out.println(INPUT_MONEY_MESSAGE.getMessage());
		final String money = scanner.nextLine();
		try {
			validateMoney(money);
			return money;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputMoney();
		}
	}

	private void validateMoney(final String money) {
		validateNumber(money);
	}

	private void validateNumber(String money) {
		if (!NUMBER_PATTERN.matcher(money).matches()) {
			throw new IllegalArgumentException(INVALID_INPUT_NUMBER_EXCEPTION.getMessage());
		}
	}

	public List<Integer> inputValidLotteryNumber() {
		final String numbers = inputLotteryNumber();
		return splitNumbers(numbers);
	}

	private String inputLotteryNumber() {
		System.out.println(INPUT_WINNING_NUMBER_MESSAGE.getMessage());
		final String winningNumber = scanner.nextLine();
		try {
			validateWinningNumber(winningNumber);
			return winningNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputLotteryNumber();
		}
	}

	private List<Integer> splitNumbers(String numbers) {
		return Arrays.stream(numbers.split(WINNING_NUMBER_DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	private void validateWinningNumber(String winningNumber) {
		if (!WINNING_NUMBER_PATTERN.matcher(winningNumber).matches()) {
			throw new IllegalArgumentException(INVALID_WINNING_NUMBER_EXCEPTION.getMessage());
		}
	}

	public int inputValidBonusNumber() {
		return Integer.parseInt(inputBonusNumber());
	}

	private String inputBonusNumber() {
		System.out.println(INPUT_BONUS_BALL_MESSAGE.getMessage());
		final String bonusNumber = scanner.nextLine();
		try {
			validateNumber(bonusNumber);
			return bonusNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputBonusNumber();
		}
	}
}
