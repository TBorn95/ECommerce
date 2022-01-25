package ePlattform.server.restControllers;

import ePlattform.server.domainObjects.Produkt;
import ePlattform.server.repositories.ProduktRepository;
import ePlattform.server.services.ProduktService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produkt")
public class ProduktController {

    private ProduktService produktService;
    private ProduktRepository produktRepository;

    public ProduktController(ProduktService produktService, ProduktRepository produktRepository) {
        this.produktService = produktService;
        this.produktRepository = produktRepository;
    }

    @GetMapping
    public Iterable<Produkt> getAll(){
        return produktRepository.findAll();
    }

    @GetMapping(value="/{id}")
    public Produkt getOne(@PathVariable("id") int id){
        return produktRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produkt konnte nicht gefunden werden"));
    }

    @PostMapping
    public Produkt newProdukt(@RequestBody Produkt produkt){
        return produktRepository.save(produkt);
    }

//    @PutMapping
//    public double setAngebotspreis(
//            @RequestParam("angebotspreis") double angebotspreis,
//            @RequestParam("produktid") int produktId)

}
