Building the image
=====================

## Observation

* For this project, we need build the image of oracle database because the image hosted in docker-hub cannot execute scripts when starting the container.

* The next steps were executed in windows 10, using the docker-cli application.

* Reference [original walkthrough](https://medium.com/oracledevs/creating-an-oracle-database-docker-image-f3cea1ea21bb)


## steps

* Download the file of oracle database from oracle page.
    * [oracle page](https://www.oracle.com/database/technologies/oracle-database-software-downloads.html)
    * we downloading the next file  (12.2.0.1.0) - Standard Edition 2 and Enterprise Edition -- Linux x86-64
    * name of the file is `linuxx64_12201_database.zip`.
* After download the file we need to clone the next repository from github in our machines
    * `https://github.com/oracle/docker-images/`

* We copy the oracle zip file inside the next directory
    * `[SOME_PATH]/docker-images-master/OracleDatabase/SingleInstance/dockerfiles/12.2.0.1/`

* We change directory to `[SOME_PATH]/docker-images-master/OracleDatabase/SingleInstance/dockerfiles/` and execute the next script
    
```
./buildDockerImage.sh -v 12.2.0.1 -e
```

* When the script is finished, we have a image of the oracle database
    * `docker image list`

```
REPOSITORY                         TAG                 IMAGE ID            CREATED             SIZE
oracle/database                    12.2.0.1-ee         3e9720ba81da        13 minutes ago      6.12GB
```

## More Observation

* This image is used in the file `oracle-db/Dockerfile`
* Run script when creating container of oracle database [Here](https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance/samples/customscripts)



