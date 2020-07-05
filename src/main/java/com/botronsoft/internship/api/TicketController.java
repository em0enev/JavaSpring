package com.botronsoft.internship.api;

import com.botronsoft.internship.Exceptions.NotFoundExceptions;
import com.botronsoft.internship.data.InMemoryDb;
import com.botronsoft.internship.models.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("enroll")
public class TicketController {
    private final InMemoryDb db;

    public TicketController(InMemoryDb db) {
        this.db = db;
    }

    @PostMapping
    public ResponseEntity enroll(@RequestBody String serviceId) {
        if (db.getServiceById(serviceId) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Ticket ticket = db.enroll(serviceId);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
}
