# Github Users Service

[![Build Status](https://travis-ci.org/smpavlenko/github-users.svg?branch=master)](https://travis-ci.org/smpavlenko/github-users)

Simple REST service written on Scala for retrieving github users using github-api(https://developer.github.com/v3/)

## How to run the service
Clone the repository:
```
> git clone https://github.com/smpavlenko/github-users.git
```

Get to the `github-users` folder:
```
> cd github-users
```

Run the service:
```
> sbt run
```

By default the service runs on port 5000

## Usage

Get list of users:
from console
```
> curl -v http://localhost:5000/users/{language}
```
or from simply browser
```
http://localhost:5000/users/{language}
```
where {language} is programming language, e.g.:
```
> curl -v http://localhost:5000/users/scala
```

__Params:__
- page - page number
- perPage - count of users per page 
```
http://localhost:5000/users/java?page=15&perPage=10
```

## Technologies

- Scala
- Akka HTTP
- Play Framework
- GitHub REST API
