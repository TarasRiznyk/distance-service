# README

Simple distance calculation service. That calculates all possible unique paths between 2 cities. Swagger added, so you check endpoint via /swagger-ui.html.
Supports concurrent requests.

Requests example:

```text
curl -X POST "http://localhost:8080/distance" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"cityOne\": \"A\",  \"cityTwo\": \"B\",  \"distance\": 120}"
curl -X POST "http://localhost:8080/distance" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"cityOne\": \"B\",  \"cityTwo\": \"C\",  \"distance\": 20}"
curl -X POST "http://localhost:8080/distance" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"cityOne\": \"A\",  \"cityTwo\": \"C\",  \"distance\": 40}"

```

```text
curl -X GET "http://localhost:8080/distance/A/B" -H  "accept: */*"

[
  {
    "cities": [
      "A",
      "B"
    ],
    "distance": 120
  },
  {
    "cities": [
      "A",
      "C",
      "B"
    ],
    "distance": 60
  }
]
```