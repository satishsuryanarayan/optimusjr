# PROBLEM
Read the problem statement [here](problem.txt).

# INSTRUCTIONS
### Requires JDK 24 and Maven 3.9.11

#### Clone the repository
`git clone https://github.com/satishsuryanarayan/optimusjr.git`

#### Change directory to the folder containing pom.xml
`cd optimusjr`

#### Start clean
`mvn clean`

#### Compile the source code
`mvn compile`

#### Run the tests
`mvn test`

#### Clean and install the binaries - this will create a _target_ directory with _optimusjr-1.0-SNAPSHOT.jar_ 
`mvn clean install` 

#### Run the jar - then provide the arguments to the program in STDIN. The result is output to STDOUT
`java -jar target/optimusjr-1.0-SNAPSHOT.jar`