package uk.co.automatictester.truststore.maven.plugin.certificate;

import uk.co.automatictester.truststore.maven.plugin.net.HttpsURLConnectionFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CertificateDownloader {

    private final boolean trustAllCerts;
    private final boolean skipHostnameVerification;

    public CertificateDownloader(boolean trustAllCerts, boolean skipHostnameVerification) {
        this.trustAllCerts = trustAllCerts;
        this.skipHostnameVerification = skipHostnameVerification;
    }

    public List<Certificate> getServerCertificates(String url) {
        HttpsURLConnection httpsUrlConnection = HttpsURLConnectionFactory.createInstance(url, trustAllCerts, skipHostnameVerification);
        Certificate[] certs;
        try {
            httpsUrlConnection.connect();
            certs = httpsUrlConnection.getServerCertificates();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(certs);
    }
}