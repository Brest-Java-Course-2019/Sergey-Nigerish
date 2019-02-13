# Phone Station Database

Creating and populating a database with test examples.

## Getting Started

### Prerequisites

install PostgreSQL10+

### Create database

    CREATE USER psdba WITH PASSWORD 'admin123';
    
    ALTER USER psdba WITH SUPERUSER CREATEROLE CREATEDB;
    
    CREATE DATABASE phonestation WITH ENCODING='UTF8' OWNER=psdba;
    

### Filling database scripts

This module contains all database scripts that should be applied on PhoneStation database with each new feature.  

We are using Flyway Maven plugin to apply changes in database.

By default it works with localhost database, which can be cleared and build again from scripts.

Before data migrate change folder:

    $ cd ./phoneStation-database

To clean the local database execute command:

    $ mvn flyway:clean

To apply changes from the scripts:

    $ mvn flyway:migrate

You can also execute both commands together:

    $ mvn flyway:clean flyway:migrate