depend common 

mvn install:install-file -Dfile=AXMLPrinter2.jar -DgroupId=axmlprinter2 -DartifactId=axmlprinter2 -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=jce-jdk13-120.jar -DgroupId=jce -DartifactId=jce-jdk13-120 -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true