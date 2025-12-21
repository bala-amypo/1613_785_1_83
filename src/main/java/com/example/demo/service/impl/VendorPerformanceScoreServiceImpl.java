@Service
public class VendorPerformanceScoreServiceImpl {

    private final VendorPerformanceScoreRepository scoreRepo;
    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final VendorTierRepository tierRepo;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepo,
            DeliveryEvaluationRepository evalRepo,
            VendorRepository vendorRepo,
            VendorTierRepository tierRepo) {

        this.scoreRepo = scoreRepo;
        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
        this.tierRepo = tierRepo;
    }

    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        List<DeliveryEvaluation> list = evalRepo.findByVendorId(vendorId);
        long total = list.size();
        long onTime = list.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long quality = list.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePct = total == 0 ? 0 : onTime * 100.0 / total;
        double qualityPct = total == 0 ? 0 : quality * 100.0 / total;

        VendorPerformanceScore s = new VendorPerformanceScore();
        s.setVendor(vendor);
        s.setOnTimePercentage(onTimePct);
        s.setQualityCompliancePercentage(qualityPct);
        s.setOverallScore((onTimePct + qualityPct) / 2);

        return scoreRepo.save(s);
    }
}
