cp -vf ./target/minidrive.jar ./dist/minidrive.jar
cp -vrf ./target/natives ./dist/natives
version=`mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | sed -n -e '/^\[.*\]/ !{ /^[0-9]/ { p; q } }'`
zip -r ./minidrive-${version}.zip ./dist