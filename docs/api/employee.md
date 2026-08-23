# Employee API

## Employee Management
### Register Employee
`POST /employee`
Register a new employee.
#### Request

```json
{
  "person": {
    "id": 123
  },
  "status": "PERMANENT",
  "joinDate": "2026-01-01"
}
```

### View Employee Profile
`GET /employee/{id}/profile`
Get employee profile by ID.
#### Path Parameter
| Parameter | Type | Required | Description |
|---|---|---|---|
| id | long | Yes | Employee ID |
#### Response
```json
{
  "id": 123,
  "name": "Mirana",
  "birthDate": "1992-02-01",
  "gender": "female",
  "registrationNo": "20250123",
  "currentPosition": "Marketing Director",
  "currentDepartment": "Marketing",
  "currentStatus": "PERMANENT",
  "currentOffice": "Jakarta",
  "currentCompany": "SilverLake Corp"
}
```

### Assign Register Number
`PATCH /employee/{id}/registration/number`
Assign a registered number to an employee.
#### Path Parameter
| Parameter | Type | Required | Description |
|---|---|---|---|
| id | long | Yes | Employee ID |
#### Request
```json
{
  "number": "2025120123"
}
```
### Assign Position
`POST /employee/{id}/assign`
Assign a position to an employee.
#### Path Parameter
| Parameter | Type | Required | Description |
|---|---|---|---|
| id | long | Yes | Employee ID |
#### Request
```json
{
  "position": {
    "id": 123
  },
  "startDate": "2026-01-01",
  "endDate": "2027-01-01"
}
```
### Assign Work Location
`POST /employee/{id}/work/site`
Assign a work location to an employee.
#### Path Parameter
| Parameter | Type | Required | Description |
|---|---|---|---|
| id | long | Yes | Employee ID |
#### Request
```json
{
  "office": {
    "id": 123
  },
  "startDate": "2026-01-01",
  "endDate": "2027-01-01"
}
```
