package andrianopasquale97.U5W2D5.controllers;


import andrianopasquale97.U5W2D5.entities.Dipendente;
import andrianopasquale97.U5W2D5.exceptions.BadRequestException;
import andrianopasquale97.U5W2D5.payloads.DipendenteDTO;
import andrianopasquale97.U5W2D5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {

    @Autowired
    private DipendenteService dipendenteService;
    // 1. - POST http://localhost:3001/dipendenti (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public DipendenteDTO saveDipendenti(@RequestBody @Validated DipendenteDTO body, BindingResult result){
        if (result.hasErrors() ) {
            throw new BadRequestException(result.getAllErrors());
        }
        System.out.println(body);
        return dipendenteService.save(body);
    }
    // 2. - GET http://localhost:3001/dipendenti
    @GetMapping("")
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        return this.dipendenteService.getDipendenti(page, size, sortBy);
    }
    // 3. - GET http://localhost:3001/dipendenti/{id}
    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable int dipendenteId) throws Exception {
        return dipendenteService.getDipendenteById(dipendenteId);
    }
    // 4. - PUT http://localhost:3001/dipendenti/{id} (+ req.bod)
    @PutMapping("/{dipendenteId}")
    public DipendenteDTO findAndUpdate(@PathVariable int dipendenteId, @RequestBody DipendenteDTO body) throws Exception {
        return dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }
    // 5. - DELETE http://localhost:3001/dipendenti/{id}
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int dipendenteId) {
        dipendenteService.findByIdAndDelete(dipendenteId);
    }

    // 6. - POST http://localhost:3001/dipendenti/image/{id} (+ req.body)
    @PostMapping("/upload/{dipendenteId}")
    public Dipendente uploadAvatar(@RequestParam("profileImage") MultipartFile image, @PathVariable int dipendenteId) throws IOException {
        return this.dipendenteService.uploadAuthorImage(image, dipendenteId);
    }
}
