mvn -q clean package
cd target
unzip -qq *.zip
vertx runmod io.core9.core9-collection-manager-v0.0.1-SNAPSHOT
