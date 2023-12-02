# Construction avec Maven
mvn clean install

# Exécution des tests avec Maven
mvn test

# Génération du rapport de couverture Jacoco
mvn jacoco:report

# Publication du rapport Jacoco en tant qu'artefact
mkdir -p artifacts/jacoco-report
cp -r target/site/jacoco/* artifacts/jacoco-report
zip -r jacoco-report.zip artifacts/jacoco-report