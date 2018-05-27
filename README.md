# movies-microservice
Repository contain simple microservice for managing movies. It is connected with approving-microservice.

**Create movie**
* *URL:*
  _/movies_

* *Method:*
  `POST`
  
* *RequestBody:*
_{
	"title": "Inception",
	"rating": 8.3,
	"director": "Christopher Nolan",
	"actors":["Leonardo DiCaprio", "Ellen Page"]
}_

   *Required:* 
   
	`title=[String]` - Title must have at least 3 characters up to 50 and can contain only letters.

	`rating=[Double]` - Rating should be between 1 and 10. 

	`director=[String]`

	`actors=[List<String>]`

**Get movie**
* *URL:*
  _/movies/{id}_

* *Method:*
  `GET`

**Update movie**
* *URL:*
  _/movies/{id}_

* *Method:*
  `PUT`
  
* *RequestBody:*
_{
	"title": "Inception",
	"rating": 8.3,
	"director": "Christopher Nolan",
	"actors":["Leonardo DiCaprio", "Ellen Page"]
}_

   *Required:* 
   
	`title=[String]` - Title must have at least 3 characters up to 50 and can contain only letters.

	`rating=[Double]` - Rating should be between 1 and 10. 

	`director=[String]`

	`actors=[List<String>]`

**Delete movie**
* *URL:*
  _/movies/{id}_

* *Method:*
  `DELETE`

**Get list of movies**
* *URL:*
  _/movies_

* *Method:*
  `GET`

**Get list of movies with rating greater than**

**Get list of sorted movies by rating**

**Add review for movie**
