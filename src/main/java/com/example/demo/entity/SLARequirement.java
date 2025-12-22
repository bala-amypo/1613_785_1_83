@Entity
public class SLARequirement {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String requirementName;

    private String description;
    private Integer maxDeliveryDays;
    private Double minQualityScore;
    private Boolean active = true;

    // getters & setters
}
