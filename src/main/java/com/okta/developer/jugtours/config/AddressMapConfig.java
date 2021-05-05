package com.okta.developer.jugtours.config;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AddressMapConfig {

    @Bean
    public Map<String, String> addressMap(){
        Map<String, String> addressMap = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader("AddressCombine.csv"))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> addressMap.put(x[0], x[1]));

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (CsvException ex) {
            ex.printStackTrace();
        }
        return addressMap;
    }
}
