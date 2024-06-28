package PaymentService.repository;

import PaymentService.entity.Payment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaymentRepository {
    private final JdbcTemplate jdbcTemplate;

    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Payment> findAll() {
        return jdbcTemplate.query("SELECT * FROM payments", new PaymentRowMapper());
    }

    public Payment findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM payments WHERE payment_id = ?", new PaymentRowMapper(), id);
    }

    public int save(Payment payment) {
        return jdbcTemplate.update("INSERT INTO payments (amount, payment_date, status) VALUES (?, ?, ?)",
                payment.getAmount(), payment.getPaymentDate(), payment.getStatus());
    }

    public int update(Payment payment) {
        return jdbcTemplate.update("UPDATE payments SET amount = ?, payment_date = ?, status = ? WHERE payment_id = ?",
                payment.getAmount(), payment.getPaymentDate(), payment.getStatus(), payment.getPaymentId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM payments WHERE payment_id = ?", id);
    }

    private static class PaymentRowMapper implements RowMapper<Payment> {
        @Override
        public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payment payment = new Payment();
            payment.setPaymentId(rs.getLong("payment_id"));
            payment.setAmount(rs.getDouble("amount"));
            payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());
            payment.setStatus(rs.getString("status"));
            return payment;
        }
    }
}

