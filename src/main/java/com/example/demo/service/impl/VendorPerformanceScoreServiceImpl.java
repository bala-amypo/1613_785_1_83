public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

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

        List<DeliveryEvaluation> evals = evalRepo.findByVendorId(vendorId);
        if (evals.isEmpty()) throw new IllegalStateException("not found");

        double onTime = evals.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count() * 100.0 / evals.size();
        double quality = evals.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count() * 100.0 / evals.size();

        VendorPerformanceScore s = new VendorPerformanceScore();
        s.setVendor(vendor);
        s.setOnTimePercentage(onTime);
        s.setQualityCompliancePercentage(quality);
        s.setOverallScore((onTime + quality) / 2);
        s.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return scoreRepo.save(s);
    }
}
