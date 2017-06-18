# spring-boot-security-apikey
Spring Boot API Key Security Auto-Configuration

[![Build Status](https://api.travis-ci.org/adilkaraoz/spring-boot-security-apikey.svg?branch=master)](https://travis-ci.org/adilkaraoz/spring-boot-security-apikey)

[ ![Download](https://api.bintray.com/packages/adilkaraoz/spring-mit/spring-boot-security-apikey/images/download.svg?version=0.1.1.RELEASE) ](https://bintray.com/adilkaraoz/spring-mit/spring-boot-security-apikey/0.1.1.RELEASE/link)

# Add dependency to your project
```text
<dependency>
	<groupId>com.adilkaraoz.spring</groupId>
	<artifactId>spring-boot-security-apikey</artifactId>
	<version>0.1.1.RELEASE</version>
</dependency>
```

# And add following line to your pom.xml
```text
    <repositories>
		<repository>
			<snapshots>
	          <enabled>false</enabled>
			</snapshots>
			<id>bintray-adilkaraoz-spring-mit</id>
			<name>bintray</name>
			<url>http://dl.bintray.com/adilkaraoz/spring-mit</url>
		</repository>
	</repositories>
	<pluginRepositories>
		<pluginRepository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
		        <enabled>false</enabled>
			</snapshots>
		</pluginRepository>
		<pluginRepository>
			<snapshots>
	          <enabled>false</enabled>
			</snapshots>
			<id>bintray-adilkaraoz-spring-mit</id>
			<name>bintray-plugins</name>
			<url>http://dl.bintray.com/adilkaraoz/spring-mit</url>
		</pluginRepository>
	</pluginRepositories>
```
