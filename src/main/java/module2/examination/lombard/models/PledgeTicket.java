package module2.examination.lombard.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "pledge_tickets")
public class PledgeTicket {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "pass_num")
    private String passportNumber;

    @Column(name = "item_price")
    private double itemPrice;
    @Column(name = "loan_size")
    private double loanSize;
    @Column(name = "created_on")
    @CreationTimestamp
    private Date createdOn;
    @Column(name = "repayment_period")
    private Date repaymentPeriod;
    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    private PledgeStatus status = PledgeStatus.ACTIVE;

}
