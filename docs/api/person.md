# Person API
**Authentication:** JWT required for all endpoints.
```http
Authorization: Bearer <access_token>
```

## Person Management
### Create Person
`POST /person`
Create a new person.
#### Request
```json
{
  "name": "Crystal Maiden",
  "gender": "female",
  "birthDate": "1992-05-15"
}
```
### View Person
`GET /person/{id}`
Get person by ID.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Person ID   |
#### Response
```json
{
  "id": 123,
  "name": "Crystal Maiden",
  "birthDate": "1992-05-15",
  "gender": "female",
  "deleted": false,
  "fileName": "photo_profile.png",
  "filePath": "/opt/document/photo_profile.png",
  "fileSize": 52000,
  "uploadDate": "2026-05-15",
  "audit": {
    "createdBy": "keycloak-id",
    "createdAt": "2026-05-15 15:23:00",
    "lastModifiedBy": "keycloak-id",
    "lastModifiedAt": "2026-05-15 15:23:00"
  }
}
```
### Rename Person
`PATCH /person/{id}/name`
Rename a person's name.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Person ID   |
#### Request
```json
{
  "name": "Crystal Maiden",
  "gender": null,
  "birthDate": null
}
```
### Change Birthday
`PATCH /person/{id}/birthday`
Update a person's birthdate.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Person ID   |
#### Request
```json
{
  "name": null,
  "gender": null,
  "birthDate": "1993-05-15"
}
```
### Update Gender
`PATCH /person/{id}/gender`
Update a person's gender.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Person ID   |
#### Request
```json
{
  "name": null,
  "gender": "male",
  "birthDate": null
}
```
### Update Photo Profile
`PATCH /person/{id}/photo/profile`
Update a person's photo profile.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Person ID   |
#### Request Part
```http
file: MultipartFile
```
### Disable Person
`DELETE /person/{id}`
Disable a person.
#### Path Parameter
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id        | long | Yes      | Person ID   |

