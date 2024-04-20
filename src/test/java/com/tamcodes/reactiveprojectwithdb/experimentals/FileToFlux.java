package com.tamcodes.reactiveprojectwithdb.experimentals;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

public class FileToFlux {

    @Test
    void fileToFlux() throws IOException, InterruptedException {

        Stream<String> lineStream = Files.lines(Path.of("src/test/resources/testfile.txt"));

        Flux.fromStream(lineStream).delayElements(Duration.ofMillis(500)).subscribe(System.out::println);

        Thread.sleep(10000);

    }
}
