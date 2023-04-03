package com.example.had;

import com.example.had.sample.Actualdata;
import com.example.had.sample.Dummydata;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HadApplication {

    private Dummydata dummydata;
    private Actualdata actualdata;

//    public HadApplication(Dummydata dummydata)
//    {
//        this.dummydata = dummydata;
//    }
    public HadApplication(Actualdata actualdata)
    {
        this.actualdata = actualdata;
    }



    public static void main(String[] args) {
        SpringApplication.run(HadApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args ->
        {
            //dummydata.generateData();
//            actualdata.generateData();
        };
    }


}