plugins {
    
    // TODO : Use BoM
    val kotlinVersion = "2.0.0-241135-2"
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
}
