@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepo;

    public VendorServiceImpl(VendorRepository vendorRepo) {
        this.vendorRepo = vendorRepo;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if(vendorRepo.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("unique");
        }
        return vendorRepo.save(vendor);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepo.findById(id).orElseThrow(() -> new IllegalStateException("not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepo.findAll();
    }

    @Override
    public Vendor deactivateVendor(Long id) {
        Vendor v = getVendorById(id);
        v.setActive(false);
        return vendorRepo.save(v);
    }
}
