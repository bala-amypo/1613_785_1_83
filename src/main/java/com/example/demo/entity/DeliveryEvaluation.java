@Entity
public class DeliveryEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private SLARequirement slaRequirement;

    private Integer actualDeliveryDays;
    private Double qualityScore;

    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    private java.util.Date evaluationDate;

    // getters & setters
}
