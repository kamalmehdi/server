rm -f *.jar ./bin/*.class && javac src/*.java -d bin/ && jar cfm server.jar Manifest.txt bin/ && java -jar server.jar;