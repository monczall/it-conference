# IT Conference

The goal of this project was to create REST Api for IT Conference system.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or above
- Maven
---
### Installation

Clone the repository

```bash
git clone https://github.com/monczall/it-conference.git
```

Navigate to project directory and
Use maven install to create application jar.

```bash
mvn clean install
```
---
### Starting the server

Execute command

```bash
java -jar target/it-conference-1.0.jar
```

Server will run on localhost:8080

---

## API Endpoints

#### Endpoint
- 'GET /api/v1/conference' - Get Conference Details
#### Example Response
```json
{
  "date": "2023-06-01",
  "startTime": "2023-06-01T10:00:00",
  "endTime": "2023-06-01T15:45:00",
  "lectures": [
    {
      "id": 1,
      "name": "Lecture_1_1",
      "pathName": "Path_1",
      "startTime": "2023-06-01T10:00:00",
      "endTime": "2023-06-01T11:45:00"
    },
    {
      "id": 2,
      "name": "Lecture_1_2",
      "pathName": "Path_2",
      "startTime": "2023-06-01T10:00:00",
      "endTime": "2023-06-01T11:45:00"
    },
    "..."
  ]
}
```
---
#### Endpoint
- 'GET /api/v1/conference/details/paths' - Get percentage interest for each path 
#### Example Response
```json
[
  {
    "pathName": "Path_1",
    "attendeesCount": 1,
    "percentOfAllAttendees": "33,3%"
  },
  {
    "pathName": "Path_2",
    "attendeesCount": 1,
    "percentOfAllAttendees": "33,3%"
  },
  {
    "pathName": "Path_3",
    "attendeesCount": 1,
    "percentOfAllAttendees": "33,3%"
  }
]
```
---
#### Endpoint
- 'GET /api/v1/conference/details/lectures' - Get percentage interest for each lecture
#### Example Response
```json
[
  {
    "lectureName": "Lecture_1_1",
    "attendeesCount": 1,
    "attendeesLimit": 5,
    "percentOfCapacity": "20%",
    "percentOfAllAttendees": "33,3%"
  },
  {
    "lectureName": "Lecture_1_2",
    "attendeesCount": 1,
    "attendeesLimit": 5,
    "percentOfCapacity": "20%",
    "percentOfAllAttendees": "33,3%"
  },
  {
    "lectureName": "Lecture_1_3",
    "attendeesCount": 1,
    "attendeesLimit": 5,
    "percentOfCapacity": "20%",
    "percentOfAllAttendees": "33,3%"
  },
  "..."
]
```
---
#### Endpoint
- 'GET /api/v1/reservations/{login}' - Get list of lectures given user is attending

#### Example Parameter
```
test2
```
#### Example Response
```json
[
  {
    "id": 2,
    "name": "Lecture_1_2",
    "pathName": "Path_2",
    "capacity": 5,
    "startTime": "2023-06-01T10:00:00",
    "endTime": "2023-06-01T11:45:00"
  }
]
```
---
#### Endpoint
- 'POST /api/v1/reservations/{lectureId}' - Make a reservation for lecture with 
given lecture ID for user given in body. When reservation is successful send confirmation email.
#### Example Parameter
```
4
```
#### Example Body
```
{
  "login": "user1",
  "email": "user1@xyz.com"
}
```
#### Example Response
```json
{
  "message": "Reservation successful",
  "lectureName": "Lecture_2_1"
}
```
---
#### Endpoint
- 'DELETE /api/v1/conference' - Cancel reservation of lecture with given lecture ID, for 
user given in body.
#### Example Parameter
```
1
```
#### Example Body
```
{
  "login": "test1"
}
```
---
#### Endpoint
- 'GET /api/v1/attendees' - Get all registered attendees
#### Example Response
```json
[
  {
    "login": "test1",
    "email": "test1@xyz.com"
  },
  {
    "login": "test2",
    "email": "test2@xyz.com"
  },
  {
    "login": "test3",
    "email": "test3@xyz.com"
  }
]
```
---
#### Endpoint
- 'PUT /api/v1/attendees' - Edit email address for user with given login
#### Example Body
```json
{
  "login": "test1",
  "email": "test1edit@xyz.com"
}
```
#### Example Response
```json
{
  "login": "test1",
  "email": "test1edit@xyz.com"
}
```
---