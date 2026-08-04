# Core module ProGuard rules
-keep class moe.rukamori.archivetune.innertube.** { *; }
-keepclassmembers class moe.rukamori.archivetune.innertube.** { *; }

# Keep Kotlin Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Ktor
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**

# NewPipe Extractor
-keep class org.schabi.newpipe.extractor.** { *; }
-dontwarn org.schabi.newpipe.extractor.**

# Rhino
-keep class org.mozilla.javascript.** { *; }

# Re2j
-keep class com.google.re2j.** { *; }
