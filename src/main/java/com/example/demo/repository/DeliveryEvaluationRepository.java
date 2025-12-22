public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {

    List<DeliveryEvaluation> findByVendorId(Long vendorId);
    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId);

    @Query("SELECT d FROM DeliveryEvaluation d WHERE d.vendor = :vendor AND d.qualityScore >= :minScore")
    List<DeliveryEvaluation> findHighQualityDeliveries(Vendor vendor, Double minScore);

    @Query("SELECT d FROM DeliveryEvaluation d WHERE d.slaRequirement = :sla AND d.meetsDeliveryTarget = true")
    List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement sla);
}
