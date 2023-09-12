package bank.mysuperbank_v1;


import bank.mysuperbank_v1.models.Role;
import bank.mysuperbank_v1.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySuperBankV1Application implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Autowired
    public MySuperBankV1Application(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MySuperBankV1Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
//        Role role1 = new Role("ADMIN");
//        roleRepository.save(role1);
    }
}
