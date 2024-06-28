package PaymentService.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Payment {
    private Long paymentId;
    private Double amount;
    private LocalDateTime paymentDate;
    private String status;

}

