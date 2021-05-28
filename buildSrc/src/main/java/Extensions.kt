import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

internal fun DependencyHandler.implementation(dependencyNotation: String) {
    add("implementation", dependencyNotation)
}

internal fun DependencyHandler.androidTestImplementation(dependencyNotation: String) {
    add("androidTestImplementation", dependencyNotation)
}

internal fun DependencyHandler.debugImplementation(dependencyNotation: String) {
    add("debugImplementation", dependencyNotation)
}

internal fun DependencyHandler.releaseImplementation(dependencyNotation: String) {
    add("releaseImplementation", dependencyNotation)
}

internal fun DependencyHandler.kapt(dependencyNotation: String) {
    add("kapt", dependencyNotation)
}

internal fun DependencyHandler.annotationProcessor(dependencyNotation: String) {
    add("annotationProcessor", dependencyNotation)
}

internal fun DependencyHandler.implementationByExclude(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
) {
    addDependencyTo(this, "implementation", dependencyNotation, dependencyConfiguration)
}
