@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository repo;

    public VendorServiceImpl(VendorRepository repo) {
        this.repo = repo;
    }

    public Vendor createVendor(Vendor vendor) {
        if (repo.existsByName(vendor.getName()))
            throw new IllegalArgumentException("Vendor name must be unique");
        return repo.save(vendor);
    }

    public Vendor getVendorById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
    }
}
