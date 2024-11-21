package contain.basic.crud_jwt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import contain.basic.crud_jwt.secure.Officials;
import contain.basic.crud_jwt.secure.OfficialsService;

@RestController
public class Associate {
    // dependency injection
    @Autowired
    Manager manager;

    // @Autowired
    // PasswordEncoder passwordEncoder;

    @Autowired
    OfficialsService officialsService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/login")
    public String login(@RequestBody Officials officials){
        return officialsService.verify(officials);
    }

    @PostMapping("/signup")
    public Officials register(@RequestBody Officials officials){
        officials.setPassword(encoder.encode(officials.getPassword()));
        return officialsService.signUP(officials);
    }

    @DeleteMapping("/memory/{size}")
    public String removeByMemory(@PathVariable("size") int size){
        return manager.deleteByMemory(size)+" laptop's are discontinued";
    }

    @PutMapping("/offer/{brand}")
    public String offerByBrand(@PathVariable("brand") String brand){
        return manager.updateBrandDiscount(brand)+" laptop's are discounted";
    }

    @PutMapping("/festival")
    public String offerDiscount(){
        return manager.updateByDiscount()+" laptop's are available for disount";
    }

    @GetMapping("/filter/{size}/{amount}")
    public List<Laptop> readManyConditionSQL(@PathVariable("size") int size, @PathVariable("amount") int amount){
        return manager.fetchManyBySizeAndAmount(size, amount);
    }

    @GetMapping("/laps")
    public List<String> readModels(){
        return manager.fetchMoreModels();
    }

    @GetMapping("/price/{cash}")
    public List<Laptop> readMoreViaCost(@PathVariable("cash") int cash){
        return manager.fetchMoreByAmount(cash);
    }

    @GetMapping("/cost/{ssd}")
    public List<Laptop> readMultipleFromLaptops(@PathVariable("ssd") int ssd){
        return manager.findAllByLaptopPrice(ssd);
    }

    @GetMapping("/memory/{ssd}")
    public List<Laptop> readManyFromLaptops(@PathVariable("ssd") int ssd){
        return manager.findAllByLaptopSsd(ssd);
    }

    @DeleteMapping("/{model}")
    public String deleteRecordById(@PathVariable("model") String model){
        if(manager.findById(model).isPresent()){
            manager.deleteById(model);
            return model+" has been sold out";
        }
        else
            return model+" not available";
    }

    @DeleteMapping("/")
    public String deleteRecord(@RequestBody Laptop laptop){
        if(manager.findById(laptop.getLaptopModel()).isPresent()){
            manager.delete(laptop);
            return laptop.getLaptopModel()+" has sold out";
        }
        return laptop.getLaptopModel()+" not available";
    }

    @GetMapping("/{model}")
    public Optional<Laptop> readByPrimaryKey(@PathVariable("model") String model){
        return manager.findById(model);
    }

    // create a record/ update record by passing whole proeprty with value
    @PostMapping("/")
    public Laptop createRecord(@RequestBody Laptop laptop){
        manager.save(laptop);
        return laptop;
    }

    // read
    @GetMapping("/")
    public List<Laptop> readingAll(){
        return (List<Laptop>) manager.findAll();
    }
}
