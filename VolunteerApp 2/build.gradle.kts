// Top-level build file where you can add configuration options common to all sub-projects/modules.
// AGP 9's built-in Kotlin compiler support means Kotlin sources are compiled
// automatically - no separate org.jetbrains.kotlin.android plugin needed.
plugins {
    alias(libs.plugins.android.application) apply false
}
