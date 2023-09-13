package bank.mysuperbank_v1.repositories;

import bank.mysuperbank_v1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleById(Long id);
    Optional<Role> findRoleByName(String name);
    boolean existsByName(String roleName);
}
