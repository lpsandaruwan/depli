[![Build Status](https://travis-ci.org/lpsandaruwan/depli.png)](https://travis-ci.org/lpsandaruwan/depli) [![Quality Gate](https://sonarqube.com/api/badges/gate?key=com.sonarqube.lpsandaruwan.depli)](https://sonarcloud.io/dashboard?id=com.sonarqube.lpsandaruwan.depli)


# DEPLI

###### A Rich JVM Monitor.

Depli is a handsome tool that lets you monitor multiple JVM connections at once though Java management bean server connections, has been built using Spring-boot and angular material.

## Requirements
Make sure you have installed Java version 7 or later.


### Run Depli using a release
To run depli from a [release](https://github.com/lpsandaruwan/depli/releases) extract the archive and,

```bash
./start.sh
```


### Build using source

Make sure you have configured maven. To obtain the standalone jar file,
```
mvn clean install -DskipTests
```

To run for the first time, to create datasource(h2 db file),
```bash
java -jar depli-*.jar \
--spring.datasource.url=jdbc:h2:file:./depli \
--spring.jpa.hibernate.ddl-auto=create \
--spring.datasource.initialize=true
```

Afterwards just running jar is enough,

```bash
java -jar depli-*.jar
```

Depli's default port is 8080, and if you run want to run it using another port use,
```bash
java -jar depli-*.jar --server.port=9000
```


### Depli's features and next
- [x] Add/edit/remove connection data
- [x] Class paths and artifacts search and lookup
- [x] Create JMX connections with username/password authentication
- [x] H2 file database/save connection data
- [x] Hot plug and unplug JMX connections
- [x] Multiple JMX connections
- [ ] Push notifications on user specific triggers and events
- [ ] User management and authentication.
- [x] Threads/stacktrace search and lookup


### Contributing
Please refer [CONTRIBUTING.md](https://github.com/lpsandaruwan/depli/blob/master/CONTRIBUTING.md)


### License

Copyright (c) 2017: Lahiru Pathirage <lpsandaruwan@gmail.com>

Depli is a free application: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
Depli is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
See COPYING for a copy of the GNU General Public License.
If not, see http://www.gnu.org/licenses/.
