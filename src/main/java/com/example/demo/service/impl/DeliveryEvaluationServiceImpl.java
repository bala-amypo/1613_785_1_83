@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository evalRepo,
            VendorRepository vendorRepo,
            SLARequirementRepository slaRepo) {

        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    public DeliveryEvaluation createEvaluation(DeliveryEvaluation e) {

        if (!e.getVendor().getActive())
            throw new IllegalStateException("Only active vendors allowed");

        if (e.getActualDeliveryDays() < 0)
            throw new IllegalArgumentException(">= 0");

        if (e.getQualityScore() < 0 || e.getQualityScore() > 100)
            throw new IllegalArgumentException("Quality score between 0 and 100");

        SLARequirement sla = e.getSlaRequirement();

        e.setMeetsDeliveryTarget(
                e.getActualDeliveryDays() <= sla.getMaxDeliveryDays());

        e.setMeetsQualityTarget(
                e.getQualityScore() >= sla.getMinQualityScore());

        return evalRepo.save(e);
    }
}
