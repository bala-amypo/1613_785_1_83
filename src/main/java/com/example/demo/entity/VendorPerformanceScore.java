@Entity
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;

    private Timestamp calculatedAt;

    @PrePersist
    void onCalc() {
        calculatedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
