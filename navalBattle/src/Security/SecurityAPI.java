package Security;

import java.io.File;
import java.io.FileInputStream;

import java.lang.ProcessBuilder.Redirect;
import java.security.KeyStore;


import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class SecurityAPI {
	public static final File KEYSTORE_FILE = new File("Keystore","navalBattle.jks");

	public static final String KEYSTORE_PASSWORD = "navalBattle_store";

	public static final String KEY_PASSWORD = "navalBattle_key";

	public static final String INSTANCE_TYPE = "JKS";

	public static final String FACTORIES_ALGORITHM = "SunX509";

	public static final String SSL_PROTOCOL = "TLS";

	public static void generateCertificate() {
		if(KEYSTORE_FILE.exists())
			return;
		
		File keytool = new File(System.getProperty("java.home"), "bin/keytool");
		new File("Keystore").mkdirs();
		String[] genkeyCmd = new String[] { keytool.toString(), "-genkey", "-keyalg", "RSA", "-alias",
				"navalBattle_alias", "-validity", "365", "-keysize", "2048", "-dname",
				"cn=navalBattle,ou=SDIS,o=FEUP,c=PT", "-keystore", KEYSTORE_FILE.getAbsolutePath(), "-storepass",
				KEYSTORE_PASSWORD, "-keypass", KEY_PASSWORD };

		ProcessBuilder processBuilder = new ProcessBuilder(genkeyCmd);
		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput(Redirect.INHERIT);
		processBuilder.redirectError(Redirect.INHERIT);

		try {
            Process exec = processBuilder.start();
            exec.waitFor();
        } catch (java.io.IOException | java.lang.InterruptedException e) {
            System.err.println("Failed to generate Certificate. Communication Proceeding without certification");
        }
	}

	public static SSLContext getSSLContext() {
		try {
			KeyStore ks = KeyStore.getInstance(INSTANCE_TYPE);
			ks.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PASSWORD.toCharArray());

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(FACTORIES_ALGORITHM);
			kmf.init(ks, KEY_PASSWORD.toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(FACTORIES_ALGORITHM);
			tmf.init(ks);

			SSLContext sslContext = SSLContext.getInstance(SSL_PROTOCOL);
			sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
			return sslContext;
		} catch (Exception e) {
			System.err.println("There was a problem getting the SSLContext! Verify if the KeyStore file exists");
			return null;
		}
	}

}
