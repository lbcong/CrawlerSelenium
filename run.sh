java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
tar -xvjf firefox-55.0b13.tar.bz2
tar -zxvf geckodriver-v0.18.0-linux64.tar.gz
tar -zxvf libs.tar.gz
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/app/libs