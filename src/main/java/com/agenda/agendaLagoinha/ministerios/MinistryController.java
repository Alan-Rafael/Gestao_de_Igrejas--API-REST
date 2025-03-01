package com.agenda.agendaLagoinha.ministerios;

import com.agenda.agendaLagoinha.View.ViewMinistry;
import com.agenda.agendaLagoinha.requests.ministriesRequests.CreateMinistryRequest;
import com.agenda.agendaLagoinha.requests.ministriesRequests.UpdateMinistryRequest;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/agenda/ministry")
public class MinistryController {

    private final MinistryService ministryService;

    public  MinistryController( MinistryService ministryService){
        this.ministryService = ministryService;
    }

    @PostMapping
    @JsonView(ViewMinistry.Base.class)
    public Object create(@RequestBody CreateMinistryRequest createMinistryRequest, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.ministryService.insert(request, createMinistryRequest));
    }

    @JsonView(ViewMinistry.Base.class)
    @GetMapping
    public List<Ministry> getMinistries(){
        return this.ministryService.getAllMisitry();
    }

    @GetMapping("/{id}")
    @JsonView(ViewMinistry.Admin.class)
    public ResponseEntity<Ministry> getMinistryOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).
                body(this.ministryService.getOneMinistry(id));
    }

    @DeleteMapping("/{id}")
    @JsonView(ViewMinistry.Base.class)
    public void deleteMinistry(@PathVariable Long id){
        this.ministryService.deleteOne(id);
    }

    @PutMapping("{id}")
    @JsonView(ViewMinistry.Base.class)
    public void updateEvent(@PathVariable Long id, @RequestBody UpdateMinistryRequest updateMinistryRequest){
        this.ministryService.updateMinistry(id, updateMinistryRequest);
    }

}
