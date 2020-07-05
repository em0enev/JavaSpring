package com.botronsoft.internship.api;

import com.botronsoft.internship.data.InMemoryDb;
import com.botronsoft.internship.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("services")
public class ServicesController {
    private final InMemoryDb db;

    @Autowired
    public ServicesController(InMemoryDb db) {
        this.db = db;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Service>> getAllServices() {
        ArrayList<Service> services = db.getAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
}
