package pl.karoldominiak.example;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchitectureLayersTest {

    @Test
    public void shouldVerifyCorrectnessOfProjectArchitecture() {
        // given
        final JavaClasses importedClasses = new ClassFileImporter().importPackages("pl.karoldominiak.example");

        // when
        final Architectures.LayeredArchitecture architectureRules = layeredArchitecture()
                .layer("PortAdapter")
                .definedBy("pl.karoldominiak.example.port.adapter..")

                .layer("Application")
                .definedBy("pl.karoldominiak.example.application..")

                .layer("Domain")
                .definedBy("pl.karoldominiak.example.domain..")

                .whereLayer("PortAdapter").mayNotBeAccessedByAnyLayer()
                .whereLayer("Application").mayOnlyBeAccessedByLayers("PortAdapter")
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "PortAdapter");

        // then
        architectureRules.check(importedClasses);
    }
}
