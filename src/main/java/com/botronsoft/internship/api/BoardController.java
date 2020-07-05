package com.botronsoft.internship.api;

import com.botronsoft.internship.data.InMemoryDb;
import com.botronsoft.internship.models.DeskCustomer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.management.Descriptor;
import java.util.List;

@RestController
@RequestMapping("board")
public class BoardController {
    private final InMemoryDb db;

    public BoardController(InMemoryDb db) {
        this.db = db;
    }

    @GetMapping
    public ResponseEntity<List<DeskCustomer>> getTopNRecords(@RequestParam("top") int topRecords){
        List<DeskCustomer> records = db.getTopRecords(topRecords);

        return new ResponseEntity<>(records, HttpStatus.OK);
    }
}
