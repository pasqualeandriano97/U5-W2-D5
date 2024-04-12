package andrianopasquale97.U5W2D5.services;


import andrianopasquale97.U5W2D5.entities.Dipendente;
import andrianopasquale97.U5W2D5.entities.Dispositivo;
import andrianopasquale97.U5W2D5.enums.stato;

import andrianopasquale97.U5W2D5.exceptions.BadRequestException;
import andrianopasquale97.U5W2D5.exceptions.NotFoundException;
import andrianopasquale97.U5W2D5.payloads.DispositivoDTO;
import andrianopasquale97.U5W2D5.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import andrianopasquale97.U5W2D5.enums.tipologie;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoDAO dispositivoDAO;
    @Autowired
    private DipendenteService dipendenteService;

    public DispositivoDTO save(DispositivoDTO newDispositivo) {

        Dispositivo dispositivo = new Dispositivo(tipologie(newDispositivo.tipologia().toString()), statodispositivo(newDispositivo.stato()));

        dispositivoDAO.save(dispositivo);
        return newDispositivo;
    }

    public Page<Dispositivo> getDispositivi(int page, int size, String sortBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return dispositivoDAO.findAll(pageable);
    }


    public Dispositivo getDispositivoById(int id) {
        return dispositivoDAO.findById(id).orElseThrow(()-> new NotFoundException("Dispositivo non trovato"));
    }

    public void findByIdAndDelete(int id) {
        Dispositivo found = this.getDispositivoById(id);
        this.dispositivoDAO.delete(found);
    }


    public Dispositivo update(int id, DispositivoDTO newDispositivo) {
        Dispositivo found = this.getDispositivoById(id);
        found.setTipologia(tipologie(newDispositivo.tipologia()));
        found.setStato(statodispositivo(newDispositivo.stato()));
        return found;
    }

    public Dispositivo findAndAssociate(int idDispositivo, int idDipendente) {
        Dispositivo found = this.getDispositivoById(idDispositivo);
        Dipendente dipendente = this.dipendenteService.getDipendenteById(idDipendente);
        if (found.getStato().equals(stato.ASSEGNATO)||(found.getStato().equals(stato.DISMESSO))||(found.getStato().equals(stato.IN_MANUTENZIONE))) {
            throw new BadRequestException("Dispositivo già in manutenzione o assegnato");
        }else{
            found.setStato(stato.ASSEGNATO);
            found.setDipendente(dipendente);
            dispositivoDAO.save(found);
        return found;}
    }

    private tipologie tipologie(String tipologia) {
        if (tipologia == null) {
            throw new BadRequestException("tipologia non valida");}
         else {
        return switch (tipologia) {
            case "cellulare" -> tipologie.CELLULARE;
            case "laptop" -> tipologie.LAPTOP;
            case "tablet" -> tipologie.TABLET;
            default -> throw new BadRequestException("tipologia non valida");
        };}
    }
    private stato statodispositivo(String statoD) {
        return switch (statoD) {
            case "disponibile" -> stato.DISPONIBILE;
            case "assegnato" -> stato.ASSEGNATO;
            case "in_manutenzione" -> stato.IN_MANUTENZIONE;
            case "dismesso" -> stato.DISMESSO;
            default -> throw new BadRequestException("stato non valido");
        };
    }

}
