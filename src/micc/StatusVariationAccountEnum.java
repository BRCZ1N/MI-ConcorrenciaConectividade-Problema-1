package micc;

public enum StatusVariationAccountEnum {

	HIGH("HIGH VARIATION"), NORMAL("NORMAL VARIATION");

	private String statusVariationAccount;

	private StatusVariationAccountEnum(String statusVariationAccount) {

		this.statusVariationAccount = statusVariationAccount;

	}

	public String getStatusVariationAccount() {
		return statusVariationAccount;
	}

	@Override
	public String toString() {

		return this.statusVariationAccount;

	}

}
