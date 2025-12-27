// VendorService.java
package com.example.demo.service;
import com.example.demo.model.Vendor;
import java.util.List;

public interface VendorService {
    Vendor createVendor(Vendor vendor);
    Vendor updateVendor(Long id, Vendor vendor);
    Vendor getVendorById(Long id);
    List<Vendor> getAllVendors();
    void deactivateVendor(Long id);
}

// SLARequirementService.java
package com.example.demo.service;
import com.example.demo.model.SLARequirement;

public interface SLARequirementService {
    SLARequirement createRequirement(SLARequirement requirement);
    SLARequirement updateRequirement(Long id, SLARequirement requirement);
    void deactivateRequirement(Long id);
}

// DeliveryEvaluationService.java
package com.example.demo.service;
import com.example.demo.model.DeliveryEvaluation;
import java.util.List;

public interface DeliveryEvaluationService {
    DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation);
    List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId);
    List<DeliveryEvaluation> getEvaluationsForRequirement(Long slaId);
}

// VendorPerformanceScoreService.java
package com.example.demo.service;
import com.example.demo.model.VendorPerformanceScore;
import java.util.List;

public interface VendorPerformanceScoreService {
    VendorPerformanceScore calculatePerformanceScore(Long vendorId);
    VendorPerformanceScore calculateScore(Long vendorId);
    VendorPerformanceScore getLatestScore(Long vendorId);
    List<VendorPerformanceScore> getScoresForVendor(Long vendorId);
}

// VendorTierService.java
package com.example.demo.service;
import com.example.demo.model.VendorTier;
import java.util.List;

public interface VendorTierService {
    VendorTier createTier(VendorTier tier);
    String assignTier(Long vendorId);
    void deactivateTier(Long id);
}
