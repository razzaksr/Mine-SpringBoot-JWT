package contain.basic.crud_jwt.secure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficialsRepository extends JpaRepository<Officials,String> {
    
}
