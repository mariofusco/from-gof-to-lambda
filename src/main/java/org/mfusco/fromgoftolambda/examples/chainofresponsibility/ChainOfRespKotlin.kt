package org.mfusco.fromgoftolambda.examples.chainofresponsibility

fun parseText(file: File) = if (file.type == File.Type.TEXT) "Text file: ${file.content}" else null
fun parsePresentation(file: File) = if (file.type == File.Type.PRESENTATION) "Presentation file: ${file.content}" else null
fun parseAudio(file: File) = if (file.type == File.Type.AUDIO) "Audio file: ${file.content}" else null
fun parseVideo(file: File) = if (file.type == File.Type.VIDEO) "Video file: ${file.content}" else null

fun main(args: Array<String>) {
    val file = File(File.Type.AUDIO, "Dream Theater  - The Astonishing")

    println(listOf(::parseText, ::parsePresentation, ::parseVideo, ::parseAudio)
            .mapNotNull { it(file) }
            .firstOrNull() ?: RuntimeException("Unknown file $file"))
}
