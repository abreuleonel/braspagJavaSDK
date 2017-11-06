package braspag.sdk.api;

import com.google.gson.annotations.SerializedName;

public class Payment {
	@SerializedName("Type")
	private Type type;
	
	@SerializedName("Amount")
	private String amount;
	
	@SerializedName("Currency")
	private Currency currency;
	
	@SerializedName("Country")
	private String country;
	
	@SerializedName("Provider")
	private Provider provider;
	
	@SerializedName("ServiceTaxAmount")
	private Integer serviceTaxAmount;
	
	@SerializedName("Installments")
	private Integer installments;
	
	@SerializedName("Interest")
	private String interest;
	
	@SerializedName("Capture")
	private boolean capture = false;
	
	@SerializedName("Authenticate")
	private boolean authenticate = false;
	
	@SerializedName("IsRecurring")
	private boolean recurrent;
	
	@SerializedName("SoftDescriptor")
	private String softDescriptor = "";
	
	@SerializedName("ReturnUrl")
	private String returnUrl;
	
	@SerializedName("ExtraDataCollection")
	private Object extraDataCollection[];

	@SerializedName("CreditCard")
	private CreditCard creditCard;
	
	@SerializedName("AcquirerTransactionId")
	private String acquirerTransactionId;
	
	@SerializedName("ProofOfSale")
	private String proofOfSale;
	
	@SerializedName("AuthorizationCode")
	private String authorizationCode;
	
	@SerializedName("PaymentId")
	private String paymentId;
	
	@SerializedName("ReceivedDate")
	private String receivedDate;
	
	@SerializedName("CaptureAmount")
	private Integer capturedAmount;
	
	@SerializedName("CapturedDate")
	private String capturedDate;
	
	@SerializedName("ReasonCode")
	private String reasonCode;
	
	@SerializedName("ReasonMessage")
	private String reasonMessage;
	
	@SerializedName("ProviderReturnCode")
	private String providerReturnCode;
	
	@SerializedName("ProviderReturnMessage")
	private String providerReturnMessage;
	
	@SerializedName("Status")
	private Integer status;
	
	@SerializedName("Links")
	private Object links[];
	
	@SerializedName("AuthenticationUrl")
	private String authenticationUrl;
	
	@SerializedName("Url")
	private String url;
	
	@SerializedName("ExpirationDate")
	private String expirationDate;
	
	@SerializedName("BoletoNumber")
	private String boletoNumber;
	
	@SerializedName("BarCodeNumber")
	private String barCodeNumber;
	
	@SerializedName("DigitableLine")
	private String digitableLine;
	
	@SerializedName("Address")
	private String address;
	
	@SerializedName("RecurrentPayment")
	private RecurrentPayment recurrentPayment;

	public Payment(String amount, Integer installments) {
		setAmount(amount);
		setInstallments(installments);
	}

	public Payment(String amount) {
		this(amount, 1);
	}

	public String getAcquirerTransactionId() {
		return acquirerTransactionId;
	}

	public Payment setAcquirerTransactionId(String acquirerTransactionId) {
		this.acquirerTransactionId = acquirerTransactionId;
		return this;
	}

	public String getAuthenticationUrl() {
		return authenticationUrl;
	}

	public Payment setAuthenticationUrl(String authenticationUrl) {
		this.authenticationUrl = authenticationUrl;
		return this;
	}

	public String getProviderReturnCode() {
		return providerReturnCode;
	}

	public Payment setProviderReturnCode(String providerReturnCode) {
		this.providerReturnCode = providerReturnCode;
		return this;
	}

	public String getProviderReturnMessage() {
		return providerReturnMessage;
	}

	public Payment setProviderReturnMessage(String providerReturnMessage) {
		this.providerReturnMessage = providerReturnMessage;
		return this;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public Payment setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
		return this;
	}

	public String getReasonMessage() {
		return reasonMessage;
	}

	public Payment setReasonMessage(String reasonMessage) {
		this.reasonMessage = reasonMessage;
		return this;
	}

	public CreditCard creditCard(String securityCode, String brand) {
		setType(Type.CreditCard);
		setCreditCard(new CreditCard(securityCode, brand));

		return getCreditCard();
	}

	public RecurrentPayment recurrentPayment(boolean authorizeNow) {
		setRecurrentPayment(new RecurrentPayment(authorizeNow));

		return getRecurrentPayment();
	}

	public String getAmount() {
		return amount;
	}

	public Payment setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public boolean getAuthenticate() {
		return authenticate;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public Payment setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
		return this;
	}

	public boolean getCapture() {
		return capture;
	}

	public Integer getCapturedAmount() {
		return capturedAmount;
	}

	public Payment setCapturedAmount(Integer capturedAmount) {
		this.capturedAmount = capturedAmount;
		return this;
	}

	public String getCapturedDate() {
		return capturedDate;
	}

	public Payment setCapturedDate(String capturedDate) {
		this.capturedDate = capturedDate;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Payment setCountry(String country) {
		this.country = country;
		return this;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public Payment setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
		return this;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Payment setCurrency(Currency currency) {
		this.currency = currency;
		return this;
	}

	public Integer getInstallments() {
		return installments;
	}

	public Payment setInstallments(Integer installments) {
		this.installments = installments;
		return this;
	}

	public String getInterest() {
		return interest;
	}

	public Payment setInterest(String interest) {
		this.interest = interest;
		return this;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public Payment setPaymentId(String paymentId) {
		this.paymentId = paymentId;
		return this;
	}

	public String getProofOfSale() {
		return proofOfSale;
	}

	public Payment setProofOfSale(String proofOfSale) {
		this.proofOfSale = proofOfSale;
		return this;
	}

	public Provider getProvider() {
		return provider;
	}

	public Payment setProvider(Provider provider) {
		this.provider = provider;
		return this;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public Payment setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
		return this;
	}

	public boolean isRecurrent() {
		return recurrent;
	}

	public Payment setRecurrent(boolean recurrent) {
		this.recurrent = recurrent;
		return this;
	}

	public RecurrentPayment getRecurrentPayment() {
		return recurrentPayment;
	}

	public Payment setRecurrentPayment(RecurrentPayment recurrentPayment) {
		this.recurrentPayment = recurrentPayment;
		return this;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public Payment setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
		return this;
	}

	public Integer getServiceTaxAmount() {
		return serviceTaxAmount;
	}

	public Payment setServiceTaxAmount(Integer serviceTaxAmount) {
		this.serviceTaxAmount = serviceTaxAmount;
		return this;
	}

	public String getSoftDescriptor() {
		return softDescriptor;
	}

	public Payment setSoftDescriptor(String softDescriptor) {
		this.softDescriptor = softDescriptor;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public Payment setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Type getType() {
		return type;
	}

	public Payment setType(Type type) {
		this.type = type;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Payment setAddress(String address) {
		this.address = address;
		return this;
	}

	public boolean isAuthenticate() {
		return authenticate;
	}

	public Payment setAuthenticate(boolean authenticate) {
		this.authenticate = authenticate;
		return this;
	}

	public String getBarCodeNumber() {
		return barCodeNumber;
	}

	public Payment setBarCodeNumber(String barCodeNumber) {
		this.barCodeNumber = barCodeNumber;
		return this;
	}

	public boolean isCapture() {
		return capture;
	}

	public Payment setCapture(boolean capture) {
		this.capture = capture;
		return this;
	}

	public String getDigitableLine() {
		return digitableLine;
	}

	public Payment setDigitableLine(String digitableLine) {
		this.digitableLine = digitableLine;
		return this;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public Payment setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
		return this;
	}

	public Object[] getExtraDataCollection() {
		return extraDataCollection;
	}

	public Object[] getLinks() {
		return links;
	}

	public String getBoletoNumber() {
		return boletoNumber;
	}

	public Payment setBoletoNumber(String boletoNumber) {
		this.boletoNumber = boletoNumber;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Payment setUrl(String url) {
		this.url = url;
		return this;
	}

	public enum Provider {
		Bradesco, BancoDoBrasil, Simulado, ItauShopline
	}

	public enum Type {
		CreditCard, DebitCard, ElectronicTransfer, Boleto
	}

	public enum Currency {
		BRL, USD, MXN, COP, CLP, ARS, PEN, EUR, PYN, UYU, VEB, VEF, GBP
	}
}