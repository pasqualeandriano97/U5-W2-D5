package andrianopasquale97.U5W2D5.services;


import andrianopasquale97.U5W2D5.entities.Dispositivo;
import andrianopasquale97.U5W2D5.payloads.DipendenteDTO;
import andrianopasquale97.U5W2D5.payloads.DispositivoDTO;
import andrianopasquale97.U5W2D5.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoDAO dispositivoDAO;

    public DispositivoDTO save(DispositivoDTO newDispositivo) {

        Dispositivo dispositivo = new Dispositivo(newDispositivo.tipologia(), newDispositivo.stato());
        dispositivoDAO.save(dispositivo);
        return newDispositivo;

    }

}
