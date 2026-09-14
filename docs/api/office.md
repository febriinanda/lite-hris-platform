# Office API
**Authentication:** JWT required for all endpoints.
```http
Authorization: Bearer <access_token>
```

## Office Management
### Create Office
`POST /office`
Create a new office.
#### Request
```json
{
  "company": {
    "id": 123
  },
  "name": "Jakarta HQ",
  "address": "South Jakarta, Jl. TB Simatupang No. 8A",
  "type": "HEAD_QUARTER"
}
```

### View Office
`GET /office/{id}`
View office info by ID.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Office ID   |
#### Response
```json
{
  "id": 123,
  "company": {
    "id": 123
  },
  "name": "Jakarta HQ",
  "address": "South Jakarta, Jl. TB Simatupang No. 8A",
  "type": "HEAD_QUARTER",
  "deleted": false,
  "audit": {
    "createdBy": "keycloak-id",
    "createdAt": "2026-08-15 23:50:00",
    "lastModifiedBy": "keycloak-id",
    "lastModifiedAt": "2026-08-15 23:50:00"
  }
}
```

### Update Office Info
`PUT /office/{id}`
Update office info by ID.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Office ID   |
#### Request
```json
{
  "company": {
    "id": 123
  },
  "name": "Jakarta HQ",
  "address": "South Jakarta, Jl. TB Simatupang No. 8A",
  "type": "HEAD_QUARTER"
}
```

### Disable a Office
`DELETE /office/{id}`
Disable a office by ID.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Office ID   |
