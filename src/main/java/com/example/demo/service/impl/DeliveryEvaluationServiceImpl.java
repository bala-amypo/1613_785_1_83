public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository repo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository repo,
            VendorRepository vendorRepo,
            SLARequirementRepository slaRepo) {
        this.repo = repo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    public DeliveryEvaluation createEvaluation(DeliveryEvaluation e) {

        Vendor v = vendorRepo.findById(e.getVendor().getId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (!v.getActive())
            throw new IllegalStateException("active vendors");

        if (e.getActualDeliveryDays() < 0)
            throw new IllegalArgumentException(">= 0");

        if (e.getQualityScore() < 0 || e.getQualityScore() > 100)
            throw new IllegalArgumentException("between 0 and 100");

        SLARequirement sla = e.getSlaRequirement();
        e.setMeetsDeliveryTarget(e.getActualDeliveryDays() <= sla.getMaxDeliveryDays());
        e.setMeetsQualityTarget(e.getQualityScore() >= sla.getMinQualityScore());

        return repo.save(e);
    }
}
