package com.openpayd.currency;

import com.openpayd.currency.util.ExchangeRateServiceProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({ExchangeRateServiceProperties.class})
public class CurrencyConfig {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setFieldMatchingEnabled(true).
                setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return mm;
    }

}
