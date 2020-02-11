package pt.ulisboa.tecnico.learnjava.sibs.sibs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.CheckingAccount;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.cli.associateMbWayController;

public class associateMbWayControllerMethodTest {

	@Test
	public void associateMbWayControllerTest() throws ClientException, BankException, AccountException {
		Bank mockBank = mock(Bank.class);
		Client client = new Client(mockBank, "Miguel", "Carreto", "123456789", "916104499", "Rua X", 21);
		Services mockServices = mock(Services.class);
		associateMbWayController associateMbWay = new associateMbWayController();
		CheckingAccount account = new CheckingAccount(client, 100);

		when(mockServices.getAccountByIban("iban1")).thenReturn(account);

		associateMbWay.associate_mbway(mockServices, "iban1", client.getPhoneNumber());
		assertEquals(associateMbWay.getCode(), client.mbwayCode);
	}

}
