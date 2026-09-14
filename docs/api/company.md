# Company API
**Authentication:** JWT required for all endpoints.
```http
Authorization: Bearer <access_token>
```

## Company Management
### Create Company
`POST /company`
Create a new company.
#### Request
```json
{
  "name": "Silver Lake Corp"
}
```

### View Company
`GET /company/{id}`
View company info by ID.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Company ID  |
#### Response
```json
{
  "id": 123,
  "name": "Silver Lake Corp"
}
```

### Update Company Info
`PUT /company/{id}`
Update company info by ID.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Company ID  |
#### Request
```json
{
  "name": "White Lake Corp"
}
```

### Disable a Company
`DELETE /company/{id}`
Disable a company by ID.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Company ID  |
