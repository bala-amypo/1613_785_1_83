public interface SLARequirementRepository extends JpaRepository<SLARequirement, Long> {
    boolean existsByRequirementName(String name);
}
