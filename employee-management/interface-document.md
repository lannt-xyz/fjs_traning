# Employee Manage Api Interface specification

## Table of contents

1. [Employee manage api](#employee-manage-api-interface-specification)

## General parameters

- Character encoding
    - UTF-8
- Communication protocol
    - HTTPS

## Retrieve Single Employee API

### Request

- URI
    - /employee/{id}
- METHOD
    - GET

#### Header

- Content-type
    - application/json

#### Path parameters

|Required|Parameter name|Value (example)|Type|Description
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

- Trả về thông tin nhân viên được chỉ định

##### Employee information

|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|id|123|string|Employee id|
|first_name|bien|string|First name employee|
|last_name|danghuu|string|Last name employee|
|date_of_birth|1996-02-15|string|Birth date employee|
|salary|50000|number|Employee salary|

##### Sample response

```json
  {
    ""id"": ""123"",
    ""first_name"": ""bien"",    
    ""last_name"": ""danghuu"",
    ""date_of_birth"": ""1996-02-15"",
    ""salary"": 50000
  }
```

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description |
|:----|:----|:----|:----|
|400|invalid_id|Employee ID is not valid|The requested Employee ID is contains Non-Nummeric characters. |

##### Sample Error Response

- When a single error is used

```json
{
  ""code"": ""invalid_id "",
  ""message"": ""Employee ID is not valid.""
}
```

## Retrieve All Employee API

### Request

- URI
    - /employee
- METHOD
    - GET

#### Header

- Content-type
    - application/json

#### Path parameters

|Required|Parameter name|Value (example)|Type|Description
|:----|:----|:----|:----|:----|
||first_name|bien|string|First name employee|
||last_name|danghuu|string|Last name employee|
||date_of_birth_start|1996-02-15|string|Start birth date of employee|
||date_of_birth_end|1998-12-30|string|End birth date of employee|
||salary_min|1000|number|Min salary of employee|
||salary_max|50000|number|Max salary of employee|
||page|3|number|Display employee information page|
||page_count|10|number|The number of items per page. Default: 10|

#### Sample request URL

```
/employee/last_name=dang%20huu&&date_of_birth_start=1996%2D02%2D14&date_of_birth_end=1998%2D12%2D30&salary_min=1000&salary_max=60000&page_count=10&page=3
```

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 200 OK
    - 204 No Content(no data)

#### Body

- Trả về thông tin nhân viên tương ứng với điều kiện đang được mô tả tại [Query parameters](#query-parameters)
- Danh sách thông tin nhân viên được trả về sắp xếp theo employee_id

##### Sample response

```json
[
  {
    ""id"": ""123"",
    ""first_name"": ""bien"",    
    ""last_name"": ""danghuu"",
    ""date_of_birth"": ""1996-02-15"",
    ""salary"": 50000,
    ""page"": 3,
    ""page_count"": 10
  },
  {
    ""id"": ""124"",
    ""first_name"": ""luan"",   
    ""last_name"": ""danghuu"",
    ""date_of_birth"": ""1998-12-29"",
    ""salary"": 10000,
    ""page"": 3,
    ""page_count"": 10
  }
]
```

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|invalid_date_of_birth_start|Employee birthday is not valid|The requested Employee birthday is contains non-text characters. |
|400|invalid_date_of_birth_end|Employee birthday is not valid|The requested Employee birthday is contains non-text characters. |
|400|invalid_salary_min|Employee salary min is not valid|The requested Employee salary min is contains non-text characters. |
|400|invalid_salary_max|Employee salary max is not valid|The requested Employee salary max is contains non-text characters. |
|404|not_found|Not found employee|Not found employee information. |

##### Sample Error Response

- When a single error is used

```json
{
  ""code"": ""invalid_date_of_birth_start"",
  ""message"": ""Employee date of birth is not valid.""
}
```

- When returning multiple errors at the same time

```json
{
  ""errors"": [
    {
      ""code"": ""invalid_salary_min"",
      ""message"": ""Employee salary min is not valid.""
    },
    {
      ""code"": ""invalid_date_of_birth_end"",
      ""message"": ""Employee date of birth is not valid.""
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

|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|id|123|string|employee id|
|first_name|bien|string|First name employee|
|last_name|danghuu|string|Last name employee|
|date_of_birth|1996-02-15|string|Birth date of employee|
|salary|50000|number|Employee salary|

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 201 OK

#### Body

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|invalid_date_of_birth|Employee date of birth is not valid|The requested Employee date of birth is contains non-text characters. |
|400|invalid_salary|Employee salary is not valid|The requested Employee salary is contains non-text characters. |

##### Sample Error Response

- When a single error is used

```json
{
  ""code"": ""invalid_date_of_birth"",
  ""message"": ""Employee date of birth is not valid.""
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

|Key|Value (example)|Type|Description|
|:----|:----|:----|:----|
|id|123|string|employee id|
|first_name|bien|string|First name employee|
|last_name|danghuu|string|Last name employee|
|date_of_birth|1996-02-15|string|Birth date of employee|
|salary|50000|number|Employee salary|

### Response

#### Header

- Content-type
    - application/json
- HTTP status
    - 200 OK
    - 404 Not Found

#### Body

- Cập nhập thông tin nhân viên từ id được chỉ định

##### Sample response

```json
  {
    ""id"": ""123"",
    ""first_name"": ""bien"",    
    ""last_name"": ""danghuu"",
    ""date_of_birth"": ""1996-02-15"",
    ""salary"": 50000
  }
```

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description |
|:----|:----|:----|:----|
|400|invalid_id|Employee ID is not valid|The requested Employee ID is contains Non-Nummeric characters. |
|400|invalid_date_of_birth|Request employee date of birth is not valid|The requested Employee birth date is contains non-text characters. |
|400|invalid_salary|Request employee salary is not valid|The requested Employee birth salary is contains non-text characters. |
|400|required_id|Must not be empty|Return if employee id is not specified. |
|404|not_found|Not found employee|Not found employee information. |

##### Sample Error Response

- When a single error is used

```json
{
  ""code"": ""invalid_id"",
  ""message"": ""Request employee id is not valid.""
}
```

- When returning multiple errors at the same time

```json
{
  ""errors"": [
    {
      ""code"": ""invalid_date_of_birth"",
      ""message"": ""Request employee date of birth is not valid.""
    },
    {
      ""code"": ""invalid_salary"",
      ""message"": ""Request employee salary is not valid.""
    }
  ]
}
```

## Remove Employee API

### Request

- URI
    - /employee/{id}
- METHOD
    - DELETE

#### Header

- Content-type
    - application/json

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

- Xóa thông tin nhân viên được chỉ định

#### Error response

##### Parameter error

|HTTP Status|Error code|Error message|Description|
|:----|:----|:----|:----|
|400|invalid_id|Employee ID is not valid|The requested Employee ID is contains Non-Nummeric characters. |
|404|not_found|Not found employee|Not found employee information. |

##### Sample Error Response

- When a single error is used

```json
{
  ""code"": ""invalid_id"",
  ""message"": ""Request Employee id is not valid.""
}
```

- When returning multiple errors at the same time

```json
{
  ""errors"": [
    {
      ""code"": ""invalid_id"",
      ""message"": ""Request Employee id is not valid.""
    },
    {
      ""code"": ""not_found"",
      ""message"": ""Not found employee.""
    }
  ]
}
```
