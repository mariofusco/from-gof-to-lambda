package org.mfusco.fromgoftolambda.examples.chainofresponsibility

import java.util.Optional

fun parseText(file: File): Optional<String> = Optional.ofNullable(file).filter { it.type == File.Type.TEXT }.map { "Text file: " + it.content }
fun parsePresentation(file: File): Optional<String> = Optional.ofNullable(file).filter { it.type == File.Type.PRESENTATION }.map { "Presentation file: " + it.content }
fun parseAudio(file: File): Optional<String> = Optional.ofNullable(file).filter { it.type == File.Type.AUDIO }.map { "Audio file: " + it.content }
fun parseVideo(file: File): Optional<String> = Optional.ofNullable(file).filter { it.type == File.Type.VIDEO }.map { "Video file: " + it.content }

fun main(args: Array<String>) {
    val file = File(File.Type.AUDIO, "Dream Theater  - The Astonishing")

    println(listOf(::parseText, ::parsePresentation, ::parseVideo, ::parseAudio)
            .map { it(file) }
            .filter { it.isPresent }
            .first()
            .orElseThrow { RuntimeException("Unknown file " + file) }
    )
}
