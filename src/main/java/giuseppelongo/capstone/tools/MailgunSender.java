package giuseppelongo.capstone.tools;

import giuseppelongo.capstone.entities.Utente;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {
    private String apikey;
    private String domainKey;
    private String fromEmail;

    public MailgunSender(
            @Value("${mailgun.key}") String apikey,
            @Value("${mailgun.domain}") String domainKey,
            @Value("${mailgun.from}") String fromEmail) {
        this.apikey = apikey;
        this.domainKey = domainKey;
        this.fromEmail = fromEmail;
    }

    public void sendRegistrationEmail(Utente ricevente) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainKey + "/messages")
                .basicAuth("api", this.apikey)
                .queryString("from", this.fromEmail)
                .queryString("to", ricevente.getEmail())
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Ciao " + ricevente.getNome() + ", grazie per esserti registrato!")
                .asJson();

        if (response.isSuccess()) {
            System.out.println("Email inviata con successo a: " + ricevente.getEmail());
        } else {
            System.out.println("Errore durante l'invio dell'email: " + response.getBody());
        }
    }
}
