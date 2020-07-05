package com.botronsoft.internship.api;

import com.botronsoft.internship.data.InMemoryDb;
import com.botronsoft.internship.models.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("desk")
public class DeskController {
    private final InMemoryDb db;

    public DeskController(InMemoryDb db) {
        this.db = db;
    }

    @PutMapping(path = "{deskId}")
    public ResponseEntity<Object> callCustomer(@PathVariable("deskId") int deskId,
                                               @RequestBody String[] idS) {
        if (idS.length == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (String id : idS) {
            if (db.getServiceById(id) == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Ticket longestWaitingTicket = db.callForService(idS, deskId);

        Object obj = new Object() {
            public String serviceId = longestWaitingTicket.getServiceId();
            public int customerId = longestWaitingTicket.getCustomerId();
        };

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
