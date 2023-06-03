# IT Conference

The goal of this project was to create REST Api for IT Conference system.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or above
- Maven

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

### Starting the server

Execute command

```bash
java -jar target/it-conference-1.0.jar
```

Server will run on localhost:8080

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
    "OTHER LECTURES ..."
  ]
}
```
