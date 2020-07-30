import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.ex.entity"})
@ComponentScan(basePackages = {"com.ex.controller.voucher", "com.ex.service.voucher", "com.ex.util","com.ex.repo.cheque", "com.ex.converter.cheque"})
@EnableJpaRepositories(basePackages = {"com.ex.repo.cheque"})
public class VoucherBootApplication {

}
