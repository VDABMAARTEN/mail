package be.vdab.mail;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LidService {
    private final LidRepository lidRepository;
    private final LidMailing lidMailing;

    public LidService(LidRepository lidRepository, LidMailing lidMailing) {
        this.lidRepository = lidRepository;
        this.lidMailing = lidMailing;
    }

    @Transactional
    void registreer (Nieuwlid nieuwlid) throws MessagingException {
        var lid = new Lid(nieuwlid.voornaam(), nieuwlid.familienaam(), nieuwlid.emailAdres());
        lidRepository.save(lid);
        lidMailing.stuurMailNaRegistratie(lid);
    }
}
