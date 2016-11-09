package org.mfusco.fromgoftolambda.examples.chainofresponsibility;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class ChainOfRespLambda {

    public static Optional<String> parseText(File file) {
        return file.getType() == File.Type.TEXT ?
               Optional.of("Text file: " + file.getContent()) :
               Optional.empty();
    }

    public static Optional<String> parsePresentation(File file) {
        return file.getType() == File.Type.PRESENTATION ?
               Optional.of("Presentation file: " + file.getContent()) :
               Optional.empty();
    }

    public static Optional<String> parseAudio(File file) {
        return file.getType() == File.Type.AUDIO ?
               Optional.of("Audio file: " + file.getContent()) :
               Optional.empty();
    }

    public static Optional<String> parseVideo(File file) {
        return file.getType() == File.Type.VIDEO ?
               Optional.of("Video file: " + file.getContent()) :
               Optional.empty();
    }

    public static void main( String[] args ) {
        File file = new File( File.Type.AUDIO, "Dream Theater  - The Astonishing" );

        System.out.println(
        Stream.<Function<File, Optional<String>>>of(
                ChainOfRespLambda::parseText,
                ChainOfRespLambda::parsePresentation,
                ChainOfRespLambda::parseAudio,
                ChainOfRespLambda::parseVideo )
                .map(f -> f.apply( file ))
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty)) // jdk9: Optional#stream()
                .findFirst()
                .orElseThrow( () -> new RuntimeException( "Unknown file: " + file ) )
        );
    }
}
