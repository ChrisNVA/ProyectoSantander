package com.sellcom.santander.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Clase de configuracion de lectura de Properties
 * @author Luis Lopez.
 * @version 1.0.
 * @since 28/10/2019.
 *
 */
@Configuration
@PropertySource("file:${user.dir}/formateo.properties")
public class PropertiesConfig {

}
