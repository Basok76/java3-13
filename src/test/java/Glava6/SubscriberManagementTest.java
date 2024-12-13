package Glava6;
import org.example.Glava6.Subscriber.BaseSubscriber;
import org.example.Glava6.Subscriber.CorporateSubscriber;
import org.example.Glava6.Subscriber.InternetAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SubscriberManagementTest {
    private BaseSubscriber baseSubscriber;
    private InternetAccount internetSubscriber;
    private CorporateSubscriber corporateSubscriber;

    @BeforeEach
    void setUp() {
        baseSubscriber = new BaseSubscriber("John Doe", "john.doe@example.com");
        internetSubscriber = new InternetAccount("Alice Smith", "alice.smith@example.com", 100);
        corporateSubscriber = new CorporateSubscriber("Bob Manager", "bob.manager@company.com", "Tech Corp");
    }

    @Test
    void testBaseSubscriberMethods() {
        assertDoesNotThrow(() -> baseSubscriber.createContract());
        assertDoesNotThrow(() -> baseSubscriber.openAccount("+1234567890"));
        assertDoesNotThrow(() -> baseSubscriber.editAccount("Johnathan Doe", "johnathan.doe@example.com"));
        assertDoesNotThrow(() -> baseSubscriber.topUpAccount(100));
        assertDoesNotThrow(() -> baseSubscriber.changeTariffPlan("Premium"));
        assertDoesNotThrow(() -> baseSubscriber.changeOperator("NewOperator"));
        assertDoesNotThrow(baseSubscriber::viewPaymentHistory);
        assertDoesNotThrow(() -> baseSubscriber.checkBalance());
        assertDoesNotThrow(() -> baseSubscriber.checkTraffic());
        assertDoesNotThrow(baseSubscriber::closeAccount);
    }

    @Test
    void testInternetAccountMethods() {
        assertDoesNotThrow(() -> internetSubscriber.createContract());
        assertDoesNotThrow(() -> internetSubscriber.openAccount("+9876543210"));
        assertDoesNotThrow(() -> internetSubscriber.topUpAccount(200));
        assertDoesNotThrow(() -> internetSubscriber.changeTariffPlan("SuperFast"));
        assertDoesNotThrow(() -> internetSubscriber.upgradeInternetSpeed(500));
        assertDoesNotThrow(internetSubscriber::viewPaymentHistory);
        assertDoesNotThrow(() -> internetSubscriber.checkBalance());
        assertDoesNotThrow(() -> internetSubscriber.checkTraffic());
        assertDoesNotThrow(internetSubscriber::closeAccount);
    }

    @Test
    void testCorporateSubscriberMethods() {
        assertDoesNotThrow(() -> corporateSubscriber.createContract());
        assertDoesNotThrow(() -> corporateSubscriber.openAccount("+1122334455"));
        assertDoesNotThrow(() -> corporateSubscriber.editAccount("Robert Manager", "robert.manager@newcompany.com"));
        assertDoesNotThrow(() -> corporateSubscriber.setCompanyName("New Tech Corp"));
        assertDoesNotThrow(() -> corporateSubscriber.topUpAccount(500));
        assertDoesNotThrow(() -> corporateSubscriber.changeTariffPlan("Enterprise"));
        assertDoesNotThrow(corporateSubscriber::viewPaymentHistory);
        assertDoesNotThrow(() -> corporateSubscriber.checkBalance());
        assertDoesNotThrow(() -> corporateSubscriber.checkTraffic());
        assertDoesNotThrow(corporateSubscriber::closeAccount);
    }
}