package ewallet.ewallet.repository;

import ewallet.ewallet.model.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferModel, Long> {
    TransferModel findByUsername(String username);
}
