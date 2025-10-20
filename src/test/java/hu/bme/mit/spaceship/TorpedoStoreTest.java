package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        TorpedoStore store = new TorpedoStore(1);
        boolean result = store.fire(1);
        assertEquals(true, result);
    }

    @Test
    void testFireWithZero() {
        TorpedoStore store = new TorpedoStore(5);
        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(0);
        });
    }

    @Test
    void fireNegativeNumber() {
        TorpedoStore store = new TorpedoStore(5);
        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(-1);
        });
    }

    @Test
    void fireTooMany() {
        TorpedoStore store = new TorpedoStore(3);
        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(5);
        });
    }

    @Test
    void testFire3Torpedos() {
        TorpedoStore store = new TorpedoStore(5);
        boolean result = store.fire(3);
        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void testFailureRate() {
        TorpedoStore store = new TorpedoStore(5, 1.0);
        boolean result = store.fire(1);
        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fireWithNoFailure() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        boolean result = store.fire(1);
        assertTrue(result);
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void testIsEmpty() {
        TorpedoStore store = new TorpedoStore(0);
        boolean result = store.isEmpty();
        assertTrue(result);
    }

    @Test
    void isEmptyWhenNotEmpty() {
        TorpedoStore store = new TorpedoStore(5);
        boolean result = store.isEmpty();
        assertFalse(result);
    }

    @Test
    void testEmptyAfterFiring() {
        TorpedoStore store = new TorpedoStore(3, 0.0);
        store.fire(3);
        boolean result = store.isEmpty();
        assertTrue(result);
    }

    @Test
    void checkTorpedoCount() {
        TorpedoStore store = new TorpedoStore(10);
        int count = store.getTorpedoCount();
        assertEquals(10, count);
    }

    @Test
    void torpedoCountAfterFire() {
        TorpedoStore store = new TorpedoStore(10, 0.0);
        store.fire(4);
        int count = store.getTorpedoCount();
        assertEquals(6, count);
    }

    @Test
    void testConstructor() {
        TorpedoStore store = new TorpedoStore(5, 0.5);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fireMultipleTimes() {
        TorpedoStore store = new TorpedoStore(10, 0.0);
        store.fire(1);
        store.fire(2);
        assertEquals(7, store.getTorpedoCount());
    }
}
