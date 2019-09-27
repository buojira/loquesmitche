# loquesmitche
An implementation of internal project 'locksmith'

#Pendencias:
1) É necessário que os projetos broker (spring e jee) tenham um projeto em comum ("api", "core" ou algo assim) que contenha as entidades que são trafegadas. Dessa forma será possível o envio e recebimento de objetos mais complexos do que "String";

2) O projeto "locksmith" precisa de uma separação um pouco melhor de suas entidades também. É necessário conseguir deserializar as entidades sem necessitar trazer dependências desnecessárias vindas so projeto;

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/maven-plugin/)

