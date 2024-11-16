
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    kotlin("plugin.serialization") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false

    id("com.google.gms.google-services") version "4.4.2" apply false
}