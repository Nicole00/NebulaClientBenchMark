git clone https://github.com/vesoft-inc/nebula-ng-tools.git
cd nebula-ng-tools/java
mvn clean install -Dmaven.test.skip=true

cd ../../ & rm -rf nebula-ng-tools
