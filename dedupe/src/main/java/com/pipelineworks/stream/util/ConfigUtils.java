package com.pipelineworks.stream.util;

import com.pipelineworks.stream.entity.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigUtils {

   public static Configuration getConfiguration(String configPath) throws IOException {
       Yaml yaml = new Yaml();
       Configuration config;
       try( InputStream in = Files.newInputStream( Paths.get( configPath ) ) ) {
           config = yaml.loadAs( in, Configuration.class );
       }
       return config;

   }



}
