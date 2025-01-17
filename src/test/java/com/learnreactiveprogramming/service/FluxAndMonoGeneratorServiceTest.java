package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {


    FluxAndMonoGeneratorService fluxAndMonoGeneratorService=
            new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {

        var namesFlux=  fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
               // .expectNext("alex","ben","chloe")
                //.expectNextCount(3)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();

    }

    @Test
    void namesFlux_map(){

        //given
        int stringLength = 3;

        //when
        var namesFlux= fluxAndMonoGeneratorService.namesFlux_map(stringLength);
        //then
        StepVerifier.create(namesFlux)
                .expectNext("4-ALEX","5-CHLOE")
                .verifyComplete();
    }


    @Test
    void namesFlux_immutability(){

        var namesFlux= fluxAndMonoGeneratorService.namesFlux_immutability();

        StepVerifier.create(namesFlux)
                .expectNext("alex","ben","chloe")
                .verifyComplete();
    }


    @Test
    void namesFlux_flatmap(){
        //given
        int stringLength=3;

        //when
        var namesFlux=  fluxAndMonoGeneratorService.namesFlux_flatmap(stringLength);


        //then
        StepVerifier.create(namesFlux)
                .expectNext("A","L","E","X","C","H","L","O","E")
                .verifyComplete();


    }

    @Test
    void namesFlux_flatmap_async(){
        //given
        int stringLength=3;

        //when
        var namesFlux=  fluxAndMonoGeneratorService.namesFlux_flatmap_async(stringLength);


        //then
        StepVerifier.create(namesFlux)
              //  .expectNext("A","L","E","X","C","H","L","O","E")
                .expectNextCount(9)
                .verifyComplete();


    }

    @Test
    void namesFlux_concatmap(){

        //given
        int stringLength=3;

        //when
        var namesFlux=  fluxAndMonoGeneratorService.namesFlux_concatmap(stringLength);


        //then
        StepVerifier.create(namesFlux)
                .expectNext("A","L","E","X","C","H","L","O","E")
              // .expectNextCount(9)
                .verifyComplete();


    }


}
