public interface VendorRepository extends JpaRepository<Vendor, Long> {
    boolean existsByName(String name);
}
