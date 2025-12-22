@Entity
public class VendorPerformanceScore {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;

    private Timestamp calculatedAt;

    // getters & setters
}
