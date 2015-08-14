package assignment;

public class HugeInteger {
	private static final Integer NUMBER_OF_DIGITS = 40;
	private static final Character ZERO = '0';
	private static final String EMPTY_STRING = "";

	private int[] number;
	private int length;

	public HugeInteger() {
		this.length = 1;
		number = new int[length];
	}

	public HugeInteger(String digits) {
		digits = trimZeroes(digits);
		this.length = digits.length();
		this.number = new int[length];
		parse(digits);
	}
	public HugeInteger(int numberOfDigits){
		this.length=numberOfDigits;
		this.number=new int[length];
	}
	public int[] getNumber() {
		return number;
	}

	public void setNumber(int[] number) {
		this.number = number;
	}

	public Integer getLength() {
		return length;
	}

	public void parse(String digits) {
		for (int index = 0; index < digits.length(); index++) {
			number[index] = digits.charAt(index) - ZERO;
		}
	}

	public boolean isEqualTo(HugeInteger that) {

		if (this.length != that.length) {
			return false;
		}
		for (int i = 0; i < this.length; i++) {
			if (this.getNumber()[i] != that.getNumber()[i]) {
				return false;
			}
		}
		return true;
	}

	public boolean isNotEqualTo(HugeInteger that) {
		return !isEqualTo(that);
	}

	public boolean isGreaterThan(HugeInteger that) {
		if (this.length < that.length) {
			return false;
		} else if (this.length > that.length) {
			return true;

		}
		for (int i = 0; i < this.length; i++) {
			if (this.getNumber()[i] < that.getNumber()[i]) {
				return false;
			}
		}
		return this.isNotEqualTo(that);
	}

	public boolean isLessThan(HugeInteger that) {
		if (this.length > that.length) {
			return false;
		} else if (this.length < that.length) {
			return true;
		}
		for (int i = 0; i < this.length; i++) {
			if (this.getNumber()[i] > that.getNumber()[i]) {
				return false;
			}
		}
		return this.isNotEqualTo(that);
	}

	public boolean isGreaterThanOrEqualTo(HugeInteger that) {
		return isGreaterThan(that) || isEqualTo(that);
	}

	public boolean isLessThanOrEqualTo(HugeInteger that) {
		return isLessThan(that) || isEqualTo(that);
	}

	@Override
	public String toString() {
		String digits = EMPTY_STRING;
		for (Integer digit : number) {
			digits += Integer.toString(digit);
		}
		return trimZeroes(digits);
	}

	private String trimZeroes(String number) {
		int index;
		for (index = 0; index < number.length(); index++) {
			if (number.charAt(index) != ZERO) {
				break;
			}
		}
		if (index == number.length()) {
			return Character.toString(ZERO);
		} else {
			return number.substring(index);
		}
	}
}
