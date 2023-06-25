package dev.sharanggupta.parallelprocessing;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String serviceOneApiCall(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello";
    }

    public String serviceTwoApiCall(){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Beautiful";
    }

    public String databaseCall(){
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "World";
    }
}
