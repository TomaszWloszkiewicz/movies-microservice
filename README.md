# movies-microservice
Repository contain simple microservice for managing movies. It is connected with approving-microservice.

**Create movie**
* *URL:*
  _movies/_

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
  _movies/{id}_

* *Method:*
  `GET`

**Update movie**

**Delete movie**

**Get list of movies**

**Get list of movies with rating greater than**

**Get list of sorted movies by rating**

**Add review for movie**
