# Back-end Esercitazione Finale

## Descrizione
Struttura di backend sviluppata con Springboot per supportare le operazioni di CRUD su un insieme di Utenti(user) e Memo(todo).

Il JDK di riferimento è la versione **11**, il DMBS scelto è **PostgreSQL 14** e infine **Maven** gestisce le dipendenze dell'applicativo.

## Diagramma ER
![diagramma er](https://i.imgur.com/DoMhP1b.png)

## Istruzioni di utilizzo (senza Eclipse)
Il progetto va compilato con Maven tramite il comando:

```mvn clean package -U```

Per lanciare il progetto:

```java -jar target/backend-0.0.1-SNAPSHOT.jar```



### Strumenti
- [Spring Initializr](https://start.spring.io/)
- [Eclipse JEE](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)
- [Postman](https://www.postman.com/) per il debugging
- [Dbeaver](https://dbeaver.io/)

### Dipendenze
- Spring Web 
- Spring Data JPA
- PostgreSQL Driver

### Project Management
- [Maven: 3.6.3](https://maven.apache.org/)



