
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.androidApp).version(Versions.androidApp) apply false
    id(Plugins.androidLibrary).version(Versions.androidApp) apply false
    id(Plugins.kotlinAndroid).version(Versions.kotlin) apply false
    id(Plugins.daggerPlug).version(Versions.dagger) apply false
    id(Plugins.serializerPlug).version(Versions.kotlin) apply false


}
