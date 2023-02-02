package module2.examination.lombard.rest_controllers;

import module2.examination.lombard.models.PledgeTicket;
import module2.examination.lombard.repo.PledgeTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private PledgeTicketRepository ticketRepo;

    @GetMapping("/tickets")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<List<PledgeTicket>> getAllTickets(){
        List<PledgeTicket> tickets = ticketRepo.findAll();
        if (!tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tickets")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<PledgeTicket> addTicket(@RequestBody PledgeTicket ticket){
        ticketRepo.save(ticket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/tickets")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<PledgeTicket> prolongation(@RequestParam("id") long id,
                                                     @RequestParam("days") int days){
        PledgeTicket ticket = ticketRepo.findById(id).orElse(null);
        if (null!=ticket){
            ticket.setRepaymentPeriod(Date.valueOf(ticket.getRepaymentPeriod().toLocalDate().plusDays(days)));
            ticketRepo.save(ticket);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
