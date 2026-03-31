package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {

    @Id
    @Column(name = "rate_id")
    private Long id;

    @Column(name = "from_code", nullable = false, length = 10)
    private String fromCode;

    @Column(name = "to_code", nullable = false, length = 10)
    private String toCode;

    @Column(name = "factor", nullable = false)
    private Double factor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }
}

