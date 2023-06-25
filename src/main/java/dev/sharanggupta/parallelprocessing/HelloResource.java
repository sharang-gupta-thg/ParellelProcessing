package dev.sharanggupta.parallelprocessing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class HelloResource {

    HelloService helloService;

    public HelloResource(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String greet() {

        long start = System.currentTimeMillis();

        String result = helloService.serviceOneApiCall() + helloService.serviceTwoApiCall() + helloService.databaseCall();

        long end = System.currentTimeMillis();
        float totalTime = (end - start) / 1000F;

        return result + " done in: " + totalTime +" seconds";
    }

    @GetMapping("/helloAsync")
    public String greetAsync() throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> helloService.serviceOneApiCall());

        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> helloService.serviceTwoApiCall());

        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> helloService.databaseCall());


        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get();

        long end = System.currentTimeMillis();

        float totalTime = (end - start) / 1000F;

        return future1.get() + future2.get() + future3.get() + " done in: " + totalTime +" seconds";
    }
}
