package test.java.com.UEFS.system.CommonTests.CardTest;

import main.java.UEFS.system.controller.CardController;

import main.java.UEFS.system.model.Card;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class CardTest {
    private CardController cardController;

    @Before
    public void setUp() {
        cardController = new CardController();
    }

    @After
    public void tearDown() {
        cardController.deleteAll();
    }

    @Test
    public void testAddCreditCard() {
        UUID userId = UUID.randomUUID();
        Date expiryDate = new Date();

        Card card = cardController.create(
                userId,
                "John Doe",
                "Visa",
                "1234567890123456",
                "987654321",
                expiryDate,
                "123"
        );

        assertNotNull(card);
        assertEquals("John Doe", card.getUserName());
        assertEquals("Visa", card.getCardBrand());
        assertEquals("1234567890123456", card.getCardNumber());
        assertEquals("987654321", card.getAccountNumber());
        assertEquals(expiryDate, card.getExpiryDate());
        assertEquals("123", card.getCvv());
        assertTrue(card.isActive());
        assertEquals(userId, card.getUserId());

        assertEquals(1, cardController.getAll().size());

        assertEquals(card, cardController.getById(card.getId()));
    }

    @Test
    public void testRemoveCreditCard() {
        UUID userId = UUID.randomUUID();
        Date expiryDate = new Date();

        Card card = cardController.create(
                userId,
                "John Doe",
                "MasterCard",
                "9876543210987654",
                "123456789",
                expiryDate,
                "456"
        );

        cardController.delete(card.getId());

        assertEquals(0, cardController.getAll().size());
        assertNull(cardController.getById(card.getId()));
    }

    @Test
    public void testDisableCreditCard() {
        UUID userId = UUID.randomUUID();
        Date expiryDate = new Date();

        Card card = cardController.create(
                userId,
                "Jane Smith",
                "American Express",
                "5555444433332222",
                "111122223333",
                expiryDate,
                "789"
        );


        Card disabledCard = cardController.disableCreditCard(card.getId());

        assertNotNull(disabledCard);
        assertFalse(disabledCard.isActive());

        Card cardFromStore = cardController.getById(card.getId());
        assertNotNull(cardFromStore);
        assertFalse(cardFromStore.isActive());
    }
}