# Department API
**Authentication:** JWT required for all endpoints.
```http
Authorization: Bearer <access_token>
```

## Department Management
### Create Department
`POST /department`
Create a new department.
#### Request
```json
{
  "name": "Marketing"
}
```

### View Department
`GET /department/{id}`
View department info by ID.
#### Path Parameter
| Parameter | Type | Required | Description   |
|-----------|------|----------|---------------|
| id        | long | Yes      | Department ID |
#### Response
```json
{
  "id": 123,
  "name": "Marketing"
}
```

### Update Department Info
`PUT /department/{id}`
Update department info by ID.
#### Path Parameter
| Parameter | Type | Required | Description   |
|-----------|------|----------|---------------|
| id        | long | Yes      | Department ID |
#### Request
```json
{
  "name": "Marketing"
}
```

### Disable a Department
`DELETE /department/{id}`
Disable a department by ID.
#### Path Parameter
| Parameter | Type | Required | Description   |
|-----------|------|----------|---------------|
| id        | long | Yes      | Department ID |
