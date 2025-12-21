public interface VendorPerformanceScoreRepository
        extends JpaRepository<VendorPerformanceScore, Long> {

    List<VendorPerformanceScore> findByVendorOrderByCalculatedAtDesc(Long vendorId);
}
