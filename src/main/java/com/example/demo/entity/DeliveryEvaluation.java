@Entity
public class DeliveryEvaluation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private SLARequirement slaRequirement;

    private Integer actualDeliveryDays;
    private Double qualityScore;
    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    // getters & setters
}
