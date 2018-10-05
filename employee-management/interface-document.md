# Employee Manage API Interface specification

## Table of contents

1. [Detail Employee API](#detail-employee-api)
1. [Search Employee API](#search-employee-api)
1. [Register Employee API](#register-employee-api)
1. [Update Employee API](#update-employee-api)
1. [Delete Employee API](#delete-employee-api)


## General parameters

- Character encoding
    - UTF-8
- Communication protocol
    - HTTPS

## Detail Employee API

### Request

- URI
    - /employee/{id}
- METHOD
    - GET

#### Header

- Content-type
    - application/json

#### Path parameters

|Required|Parameter name|Value (example)|Type|Description|
|:----|:----|:----|:----|:----|
|○|id|123|string|Employee id|

#### Sample request URL

```
/employee/123
```

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 200 OK
    - 404 Not Found

#### Body

- Return the information of employee which was specified

##### Employee information

|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|first_name|bien|string|Employee's first name|
|last_name|danghuu|string|Employee's last name|
|date_of_birth|1996-02-15|string|Employee's Birthdate|
|salary|50000|number|Employee's salary|

##### Sample response

```json
{
  "id": "123",
  "first_name": "bien",
  "last_name": "danghuu",
  "date_of_birth": "1996-02-15",
  "salary": 50000
}
```

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|invalid_employee_id|Employee ID is invalid.|The requested Employee ID is contains Non-Nummeric characters.|
|400|invalid_employee_id_lenght|Character too long.|Length of employee ID is too long.|

##### Sample Error Response

- When returning a single error

```json
{
  "code": "invalid_employee_id ",
  "message": "Employee ID is invalid."
}
```

- When returning multiple errors at the same time

```json
{
  "errors": [
    {
      "code": "invalid_employee_id",
      "message": "Employee ID is invalid."
    },
    {
      "code": "invalid_employee_id_lenght",
      "message": "Character too long."
    }
  ]
}
```

## Search Employee API

- Returns the employee information,which corresponding to the condition was described in the [Query paramenters](#query-parameters)
- Returns the list of employee information which ascending by employee_id

### Request

- URI
    - /employee
- METHOD
    - GET

#### Header

- Content-type
    - application/json

#### Query parameters

|Required|Parameter name|Value (example)|Type|Description|
|:----|:----|:----|:----|:----|
||first_name|bien|string|Employee's first name|
||last_name|danghuu|string|Employee's last name|
||date_of_birth_from|1996-02-15|string|Birthdate of Employee|
||date_of_birth_to|1998-12-30|string|Birthdate of Employee|
||salary_min|1000|number|Min salary of Employee|
||salary_max|50000|number|Max salary of Employee|
||page|3|number|Display Employee information page|
||page_count|10|number|The number of items per page. Default: 10|

#### Sample request URL

```
/employee?last_name=dang%20huu&date_of_birth_from=1996%2D02%2D14&date_of_birth_to=1998%2D12%2D30&salary_min=1000&salary_max=60000&page_count=10&page=3
```

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 200 OK
    - 204 No Content(no data)

##### Sample response

```json
{
  "employees": [
    {
      "id": "123",
      "first_name": "bien",    
      "last_name": "danghuu",
      "date_of_birth": "1996-02-15",
      "salary": 50000
    },
    {
      "id": "124",
      "first_name": "luan",   
      "last_name": "danghuu",
      "date_of_birth": "1998-12-29",
      "salary": 10000
    }
  ],
  "pagination": {
    "page": 3,
    "count": 10,
    "total_count": 50,
    "total_page": 5
  }
}

```

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|invalid_date_of_birth_from|Date of birth is invalid.|Can not use date_of_birth as type string.|
|400|invalid_date_of_birth_to|Date of birth is invalid.|Can not use date_of_birth as type string.|
|400|invalid_salary_min|Employee salary is invalid.|Undefined for input argument of type 'char'|
|400|invalid_salary_max|Employee salary max is invalid.|Undefined for input argument of type 'char'|
|404|not_found_employee_id|ID not found.|The specified Employee ID not found.|

##### Sample Error Response

- When returning a single error

```json
{
  "code": "invalid_date_of_birth_from",
  "message": "Date of birth is invalid."
}
```

- When returning multiple errors at the same time

```json
{
  "errors": [
    {
      "code": "invalid_salary_min",
      "message": "Employee salary min is invalid."
    },
    {
      "code": "invalid_date_of_birth_to",
      "message": "Date of birth is invalid."
    }
  ]
}
```

## Register Employee API

### Request

- URI
    - /employee
- METHOD
    - POST

#### Header

- Content-type
    - application/json

#### Sample request URL

```
/employee
```

#### Sample Body

```json
{
  "id": "123",
  "first_name": "bien",
  "last_name": "danghuu",
  "date_of_birth": "1996-02-15",
  "salary": 50000
}
```

#### Request Body

|Required|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|○|id|123|string|employee id|
|○|first_name|bien|string|First name|
|○|last_name|danghuu|string|Last name|
|○|date_of_birth|1996-02-15|string|Birthdate|
||salary|50000|number|Employee's salary|

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 201 OK

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|invalid_date_of_birth|Date of birth is invalid.|Can not use date_of_birth as type string.|
|400|invalid_salary|Employee salary is invalid|Undefined for input argument of type 'char'.|

##### Sample Error Response

- When returning a single error

```json
{
  "code": "invalid_date_of_birth",
  "message": "Date of birth is invalid."
}
```

- When returning multiple errors at the same time

```json
{
  "errors": [
    {
      "code": "invalid_date_of_birth",
      "message": "Date of birth is invalid."
    },
    {
      "code": "invalid_salary",
      "message": "Employee salary is invalid."
    }
  ]
}
```

## Update Employee API

### Request

- URI
    - /employee/{id}
- METHOD
    - PUT

#### Header

- Content-type
    - application/json

#### Sample request URL

```
/employee/123
```
#### Sample Body

```json
{
  "id": "123",
  "first_name": "bien",
  "last_name": "danghuu",
  "date_of_birth": "1996-02-15",
  "salary": 50000
}
```

#### Request Body

|Required|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|○|id|123|string|employee id|
|○|first_name|bien|string|First name|
|○|last_name|danghuu|string|Last name|
|○|date_of_birth|1996-02-15|string|Birthdate|
|○|salary|50000|number|Employee's salary|

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 200 OK
    - 404 Not Found
    
#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|required_id|Must not be empty.|Return if Employee id is not specified.|
|400|invalid_employee_id|Employee ID is invalid.|The requested Employee ID is contains Non-Nummeric characters.|
|400|invalid_date_of_birth|Date of birth is invalid.|Can not use date_of_birth as type string.|
|400|invalid_salary|Employee salary is invalid.|Undefined for input argument of type 'char'.|
|404|not_found_employee_id|ID not found.|The specified Employee ID not found.|

##### Sample Error Response

- When returning a single error

```json
{
  "code": "invalid_employee_id",
  "message": "Employee ID is invalid."
}
```

- When returning multiple errors at the same time

```json
{
  "errors": [
    {
      "code": "invalid_date_of_birth",
      "message": "Date of birth is invalid."
    },
    {
      "code": "invalid_salary",
      "message": "Employee salary is invalid."
    }
  ]
}
```

## Delete Employee API

### Request

- URI
    - /employee/{id}
- METHOD
    - DELETE

#### Header

- Content-type
    - application/json
	
#### Path parameters

|Required|Parameter name|Value (example)|Type|Description|
|:----|:----|:----|:----|:----|
|○|id|123|string|Employee id|

#### Sample request URL

```
/employee/123
```

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 200 OK
    - 404 Not Found

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|invalid_employee_id|Employee ID is invalid.|The requested Employee ID is contains Non-Nummeric characters.|
|404|not_found_employee_id|ID not found.|The specified Employee ID not found.|

##### Sample Error Response

- When returning a single error

```json
{
  "code": "invalid_employee_id",
  "message": "Request Employee id is invalid."
}
```

- When returning multiple errors at the same time

```json
{
  "errors": [
    {
      "code": "invalid_employee_id",
      "message": "Employee ID is invalid."
    },
    {
      "code": "not_found_employee_id",
      "message": "ID not found."
    }
  ]
}
```
