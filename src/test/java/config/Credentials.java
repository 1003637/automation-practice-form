package config;

import org.aeonbits.owner.ConfigFactory;

public class Credentials  {
    public static CredentialsConfig credentialsConfig =
            ConfigFactory.create(CredentialsConfig.class, System.getProperties());
}
