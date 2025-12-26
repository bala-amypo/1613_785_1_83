package com.example.demo.service;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.impl.VendorTierServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class VendorTierServiceTest {

    private final VendorTierRepository repo = Mockito.mock(VendorTierRepository.class);
    private final VendorTierService service = new VendorTierServiceImpl(repo);

    @Test
    void createTier_success() {
        VendorTier tier = new VendorTier("Gold", 80);

        Mockito.when(repo.existsByTierName("Gold")).thenReturn(false);
        Mockito.when(repo.save(tier)).thenReturn(tier);

        VendorTier saved = service.createTier(tier);

        assertEquals("Gold", saved.getTierName());
        assertEquals(80, saved.getMinScoreThreshold());
        assertTrue(saved.isActive());
    }

    @Test
    void createTier_invalidThreshold() {
        VendorTier tier = new VendorTier("Silver", 120);

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.createTier(tier)
        );

        assertEquals("Threshold must be between 0–100", ex.getMessage());
    }

    @Test
    void createTier_duplicateName() {
        VendorTier tier = new VendorTier("Gold", 70);

        Mockito.when(repo.existsByTierName("Gold")).thenReturn(true);

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.createTier(tier)
        );

        assertEquals("Tier name must be unique", ex.getMessage());
    }
}
