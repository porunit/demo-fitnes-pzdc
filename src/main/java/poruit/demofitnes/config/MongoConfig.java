package poruit.demofitnes.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.connection.SslSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.util.List;
import javax.net.ssl.TrustManagerFactory;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    String mongoHost;

    @Value("${spring.data.mongodb.port}")
    String mongoPort;

    @Value("${spring.data.mongodb.database}")
    String mongoDatabase;

    @Value("${spring.data.mongodb.username}")
    String mongoUsername;

    @Value("${spring.data.mongodb.password}")
    String mongoPassword;

    @Value("${spring.data.mongodb.cert-path}")
    String mongoCertPath;

    @Bean
    public MongoClient mongoClient() throws Exception {
        byte[] caCert = Files.readAllBytes(Paths.get(mongoCertPath));
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null);
        ks.setCertificateEntry("ca", CertificateFactory.getInstance("X.509")
                .generateCertificate(new ByteArrayInputStream(caCert)));

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToSslSettings(builder -> builder.enabled(true).context(sslContext))
                .applyToClusterSettings(builder -> builder.hosts(List.of(new ServerAddress(mongoHost, Integer.parseInt(mongoPort)))))
                .credential(MongoCredential.createCredential(
                        mongoUsername,
                        mongoDatabase,
                        mongoPassword.toCharArray())
                )
                .build();

        return MongoClients.create(settings);
    }
}
