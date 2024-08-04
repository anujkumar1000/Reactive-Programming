package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {


    public Flux<String> namesFlux(){

        return Flux.fromIterable(List.of("alex","ben","chloe"))
        .log();// db or a remote service call

    }

    public Mono<String> nameMono(){

        return Mono.just("alex")
                .log();
    }


    public Flux<String> namesFlux_map(int stringlength){
  // flter the string whose length is greater than 3
        return Flux.fromIterable(List.of("alex","ben","chloe"))
                .map(String::toUpperCase)
                //.map(s-> s.toUpperCase())
                .filter(s -> s.length()>stringlength) //4-ALEX, 5-CHLOE
                .map(s->s.length()+"-"+s)
                .log();// db or a remote service call

    }

    public Flux<String> namesFlux_immutability(){

        var namesFlux= Flux.fromIterable(List.of("alex","ben","chloe"));

        namesFlux.map(String::toUpperCase);
        return  namesFlux;


    }


    public Flux<String> namesFlux_flatmap(int stringlength){
        // flter the string whose length is greater than 3
        return Flux.fromIterable(List.of("alex","ben","chloe"))
                .map(String::toUpperCase)
                //.map(s-> s.toUpperCase())
                .filter(s -> s.length()>stringlength) //4-ALEX, 5-CHLOE
                //  ALEX,CHLOE->A,L,E,X,C,H,L,O,E
                .flatMap(s -> splitString(s)) // A,L,E,X,C,H,L,O,E
                .log();// db or a remote service call

    }

    public Flux<String> namesFlux_flatmap_async(int stringlength){
        // flter the string whose length is greater than 3
        return Flux.fromIterable(List.of("alex","ben","chloe"))
                .map(String::toUpperCase)
                //.map(s-> s.toUpperCase())
                .filter(s -> s.length()>stringlength) //4-ALEX, 5-CHLOE
                //  ALEX,CHLOE->A,L,E,X,C,H,L,O,E
                .flatMap(s -> splitString_withdelay(s)) // A,L,E,X,C,H,L,O,E
                .log();// db or a remote service call

    }

    public Flux<String> namesFlux_concatmap(int stringlength){
        // flter the string whose length is greater than 3
        return Flux.fromIterable(List.of("alex","ben","chloe"))
                .map(String::toUpperCase)
                //.map(s-> s.toUpperCase())
                .filter(s -> s.length()>stringlength) //4-ALEX, 5-CHLOE
                //  ALEX,CHLOE->A,L,E,X,C,H,L,O,E
                .concatMap(s -> splitString_withdelay(s)) // A,L,E,X,C,H,L,O,E
                .log();// db or a remote service call

    }

    //ALEX-> Flux(A,L,E,X)
    public Flux<String > splitString(String name){
        var charArray= name.split("");
        return Flux.fromArray(charArray);

    }

    public Flux<String > splitString_withdelay(String name){
        var charArray= name.split("");
        //var delay =new Random().nextInt(1000);
        var delay=1000;
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));

    }


    public static void main(String[] args) {

        FluxAndMonoGeneratorService fluxAndMonoGeneratorService= new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name ->{
                    System.out.println("Name is : " + name);
                });

        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name->{
                    System.out.println("Mono name is : " + name);
                });
    }
}
