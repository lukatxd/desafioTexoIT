# Worst Movie Awards API

This is an API created by me for my TexoIT job interview. It is designed to return the award winners of the Golden Raspberry Awards for worst picture separated by producers and grouped by the difference in time to get their previous and following award.
The API runs on a default 8080 port, needs no external software installation <b>except</b> for Maven.


# How to set up the project

1 - The project uses Spring, flyway and H2 dependencies. I recommend getting Maven as depency manager, as the next steps consider you're using it to run the API.

2 - Clone the master branch of the repository, that is the release branch for this interview.

3 - Navigate to the root folder of the project (where you can find the pom.xml file) through the system explorer or a command line prompt.

4 - Execute the following command to run the API:
<b>mvn spring-boot:run</b>
This will start the Spring API and load the default movielist.csv into the memory, which is stored in the classpath.

OPTIONAL: you can pass an argument in the command line to recursevily search .csv files. The api checks the first line of the header to make sure it is a valid "movielist" file.
mvn spring-boot:run -Dspring-boot.run.arguments=--databasefiller.folder="<b>YOUR FOLDER HERE</b>"

OPTIONAL 2: If you are running it from and IDE, such as STS, you can set the arguments in the launch/debug configurations. During development I used STS 4.12.1, and the argument syntax was different.
--databasefiller.folder="<b>YOUR FOLDER HERE</b>"

# How to run tests in the API

The following maven command will run the api Use the command:
<b>mvn test</b>
OPTIONAL: 
mvn test -Ddatabasefiller.folder="<b>YOUR FOLDER HERE</b>"


# Swagger docs

Once the API is running, the swagger doc becomes available.

http://localhost:8080/api/swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config#/get-worst-movie-award-interval-controller/a


# How to make calls to the api

This API has a single GET endpoint, it can be called through the browser by inserting the url in the address bar and sending, or through a dedicated tool such as Postman. 
Once the API is running, it can be called through the following URL:
http://localhost:8080/api/worstMovie/getIntervals

# Expected outcome

{
    "min": [
        {
            "interval": 6,
            "previousWin": 1984,
            "followingWin": 1990,
            "producer": "Bo Derek"
        }
    ],
    "max": [
        {
            "interval": 13,
            "previousWin": 2002,
            "followingWin": 2015,
            "producer": "Matthew Vaughn"
        }
    ]
}
