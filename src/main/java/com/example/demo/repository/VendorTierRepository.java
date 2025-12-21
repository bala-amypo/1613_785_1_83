public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {
    boolean existsByTierName(String name);
    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc();
}
