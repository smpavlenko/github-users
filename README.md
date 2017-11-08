# Github Users Service
Simple REST service written on Scala for getting github users using github-api(https://developer.github.com/v3/)

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
curl -v http://localhost:5000/users/{language}
```
or from simply browser
```
http://localhost:5000/users/{language}
```
where {language} is programming language, e.g.:
```
curl -v http://localhost:5000/users/scala
```

## Technologies

- Scala
- Akka HTTP
- Play Framework
- GitHub REST API