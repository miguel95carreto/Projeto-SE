package pt.ulisboa.tecnico.learnjava.bank.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class Client {
	private final Set<Account> accounts = new HashSet<Account>();

	private final Bank bank;
	private String firstName;
	private String lastName;
	private String nif;
	private String phoneNumber;
	private String address;
	private int age;
	public Integer mbwayCode;
	String mbwayState;
	private IdCard idCard;

	public Client(Bank bank, IdCard idCard) {
		this.bank = bank;
		this.idCard = idCard;
	}

	public Client(Bank bank, String firstName, String lastName, String nif, String phoneNumber, String address, int age)
			throws ClientException {
		this.checkBankandPhoneNumber(bank, phoneNumber);
		this.checkNifandAge(nif, age);
		this.bank = bank;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nif = nif;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.age = age;
		this.mbwayCode = null;
		this.mbwayState = "Inactive";

		bank.addClient(this);
	}

	public String getMbwayState() {
		return this.mbwayState;
	}

	public void setMbwayState(String mbwayState) {
		this.mbwayState = mbwayState;
	}

	public Integer getMbwayCode() {
		return this.mbwayCode;
	}

	public void setMbwayCode(Integer mbwayCode) {
		this.mbwayCode = mbwayCode;
	}

	private void checkBankandPhoneNumber(Bank bank, String phoneNumber) throws ClientException {
		if ((phoneNumber.length() != 9 || !phoneNumber.matches("[0-9]+") || (bank.getClientByNif(this.nif) != null))) {
			throw new ClientException();
		}

	}

	public void checkNifandAge(String nif, int age) throws ClientException {
		if (nif.length() != 9 || !nif.matches("[0-9]+") || age < 0) {
			throw new ClientException();
		}
	}

	public void addAccount(Account account) throws ClientException {
		if (this.accounts.size() == 5) {
			throw new ClientException();
		}

		this.accounts.add(account);
	}

	public void deleteAccount(Account account) {
		this.accounts.remove(account);
	}

	public boolean hasAccount(Account account) {
		return this.accounts.contains(account);
	}

	public int getNumberOfAccounts() {
		return this.accounts.size();
	}

	public Stream<Account> getAccounts() {
		return this.accounts.stream();
	}

	public void happyBirthDay() throws BankException, AccountException, ClientException {
		this.age++;

		if (this.age == 18) {
			Set<Account> accounts = new HashSet<Account>(this.accounts);
			for (Account account : accounts) {
				YoungAccount youngAccount = (YoungAccount) account;
				youngAccount.upgrade();
			}
		}
	}

	public boolean isInactive() {
		return this.accounts.stream().allMatch(a -> a.isInactive());
	}

	public int numberOfInactiveAccounts() {
		return (int) this.accounts.stream().filter(a -> a.isInactive()).count();
	}

	public Bank getBank() {
		return this.bank;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getNif() {
		return this.nif;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
