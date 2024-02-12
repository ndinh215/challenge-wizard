package com.rapiddweller.configuration;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.KeyCredential;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "open-ai")
public class OpenAIConfig {
    @Value("secret-key")
    private String secretKey;

    @Value("model-name")
    private String modelName;

    @Bean
    public OpenAIClient getClient() {
        return new OpenAIClientBuilder()
                .credential(new KeyCredential(secretKey))
                .buildClient();
    }
}
