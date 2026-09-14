# Job Position API
**Authentication:** JWT required for all endpoints.
```http
Authorization: Bearer <access_token>
```

## Job Position Management
### Create a Job Position
`POST /job/position`
Create a new job position.
#### Request
```json
{
  "department": {
    "id": 123
  },
  "title": "Senior Programmer"
}
```

### View Job Position
`GET /job/position/{id}`
View job position info by ID.
#### Path Parameter
| Parameter | Type | Required | Description     |
|-----------|------|----------|-----------------|
| id        | long | Yes      | Job Position ID |
#### Response
```json
{
  "id": 123,
  "title": "Senior Programmer",
  "department": {
    "id": 123
  },
  "deleted": false,
  "audit": {
    "createdBy": "keycloak-id",
    "createdAt": "2026-08-15 23:50:00",
    "lastModifiedBy": "keycloak-id",
    "lastModifiedAt": "2026-08-15 23:50:00"
  }
}
```

### Update Job Position Info
`PUT /job/position/{id}`
Update job position info by ID.
#### Path Parameter
| Parameter | Type | Required | Description     |
|-----------|------|----------|-----------------|
| id        | long | Yes      | Job Position ID |
#### Request
```json
{
  "department": {
    "id": 123
  },
  "title": "Senior Programmer"
}
```

### Disable a Company
`DELETE /job/position/{id}`
Disable a job position by ID.
#### Path Parameter
| Parameter | Type | Required | Description     |
|-----------|------|----------|-----------------|
| id        | long | Yes      | Job Position ID |
