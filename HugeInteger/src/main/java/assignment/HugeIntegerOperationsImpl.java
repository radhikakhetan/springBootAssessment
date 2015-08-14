package assignment;

import java.util.Arrays;
import java.util.function.Predicate;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

@Service
public class HugeIntegerOperationsImpl implements HugeIntegerOperations {

	private static final String IS_LESS_THAN_OR_EQUAL_TO = "isLessThanOrEqualTo";
	private static final String IS_GREATER_THAN_OR_EQUAL_TO = "isGreaterThanOrEqualTo";
	private static final String IS_LESS_THAN = "isLessThan";
	private static final String IS_GREATER_THAN = "isGreaterThan";
	private static final String IS_NOT_EQUAL_TO = "isNotEqualTo";
	private static final String IS_EQUAL_TO = "isEqualTo";

	public HugeIntegerOperationsImpl() {
	}

	@Override
	public String add(String first, String second) {
		HugeInteger firstNumber = new HugeInteger(first);
		HugeInteger secondNumber = new HugeInteger(second);

		int maxLength = findMaximum(firstNumber.getLength(), secondNumber.getLength());
		HugeInteger result = new HugeInteger(maxLength + 1);

		if (firstNumber.isLessThan(secondNumber)) {
			HugeInteger tempNumber = secondNumber;
			secondNumber = firstNumber;
			firstNumber = tempNumber;
		}
		ArrayUtils.reverse(firstNumber.getNumber());
		ArrayUtils.reverse(secondNumber.getNumber());
		int index = 0;
		for (; index < findMinimum(firstNumber.getLength(), secondNumber.getLength()); index++) {
			result.getNumber()[index] += firstNumber.getNumber()[index] + secondNumber.getNumber()[index];
			if (result.getNumber()[index] > 9) {
				result.getNumber()[index + 1] += result.getNumber()[index] / 10;
				result.getNumber()[index] = result.getNumber()[index] % 10;
			}
		}
		for (; index < firstNumber.getLength(); index++) {
			result.getNumber()[index] += firstNumber.getNumber()[index];
			if (result.getNumber()[index] > 9) {
				result.getNumber()[index + 1] += result.getNumber()[index] / 10;
				result.getNumber()[index] = result.getNumber()[index] % 10;
			}
		}
		ArrayUtils.reverse(result.getNumber());
		return result.toString();

	}

	@Override
	public String subtract(String first, String second) {
		HugeInteger firstNumber = new HugeInteger(first);
		HugeInteger secondNumber = new HugeInteger(second);
		boolean isNegative = false;
		ArrayUtils.reverse(firstNumber.getNumber());
		ArrayUtils.reverse(secondNumber.getNumber());

		if (firstNumber.isLessThan(secondNumber)) {
			HugeInteger tempNumber = secondNumber;
			secondNumber = firstNumber;
			firstNumber = tempNumber;
			isNegative = true;
		}

		for (int i = 0; i < findMinimum(firstNumber.getLength(), secondNumber.getLength()); i++) {
			firstNumber.getNumber()[i] -= secondNumber.getNumber()[i];
			if (firstNumber.getNumber()[i] < 0) {
				firstNumber.getNumber()[i] += 10;
				firstNumber.getNumber()[i + 1] -= 1;
			}
		}
		ArrayUtils.reverse(firstNumber.getNumber());

		if (isNegative) {
			return "-" + firstNumber.toString();
		}
		return firstNumber.toString();
	}

	@Override
	public boolean isZero(String first) {
		HugeInteger number = new HugeInteger(first);
		return Arrays.asList(ArrayUtils.toObject(number.getNumber())).stream().map(digit -> digit == 0).reduce(true,
				(a, b) -> a && b);

	}

	@Override
	public boolean operations(String first, String second, String operator) {

		HugeInteger firstNumber = new HugeInteger(first);
		HugeInteger secondNumber = new HugeInteger(second);

		Predicate<HugeInteger> isEqualTo = b -> {
			return firstNumber.isEqualTo(b);
		};
		Predicate<HugeInteger> isNotEqualTo = b -> {
			return firstNumber.isNotEqualTo(b);
		};
		Predicate<HugeInteger> isGreaterThan = b -> {
			return firstNumber.isGreaterThan(b);
		};
		Predicate<HugeInteger> isLessThan = b -> {
			return firstNumber.isLessThan(b);
		};
		Predicate<HugeInteger> isGreaterThanOrEqualTo = b -> {
			return firstNumber.isGreaterThanOrEqualTo(b);
		};
		Predicate<HugeInteger> isLessThanOrEqualTo = b -> {
			return firstNumber.isLessThanOrEqualTo(b);
		};

		switch (operator) {

		case IS_EQUAL_TO:
			return isEqualTo.test(secondNumber);
		case IS_NOT_EQUAL_TO:
			return isNotEqualTo.test(secondNumber);
		case IS_GREATER_THAN:
			return isGreaterThan.test(secondNumber);
		case IS_LESS_THAN:
			return isLessThan.test(secondNumber);
		case IS_GREATER_THAN_OR_EQUAL_TO:
			return isGreaterThanOrEqualTo.test(secondNumber);
		case IS_LESS_THAN_OR_EQUAL_TO:
			return isLessThanOrEqualTo.test(secondNumber);
		}
		return true;
	}

	private int findMinimum(Integer a, Integer b) {
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}

	private int findMaximum(Integer a, Integer b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}
}
