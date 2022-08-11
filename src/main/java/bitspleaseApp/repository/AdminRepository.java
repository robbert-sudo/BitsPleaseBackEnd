package bitspleaseApp.repository;

import bitspleaseApp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<User, Long> {

    Iterable<User> findAllByEnabled(boolean b);

    Optional<User> findByUsername(String username);

}
