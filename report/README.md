<center><font size='8'><b>CS307 Project 2 - Report</b></font></center>

> Group Members:
>
> ​	张闻城(12010324)
>
> ​	谢宇东()

<font size='6'><b>目录</b></font>

[TOC]

# **1. Database Design**

<img src="./image/orders.png" style="zoom:50%;" />

## 1.1 `center`

`center` table has two original property: `id`, `name.`

We add expenditure and revenue to update the pay and the profit of each center when we import task1_in_stoke_test_data_publish.csv, task2_test_data_publish.csv, task34_update_test_data_publish.tsv,task34_delete_test_data_publish.tsv.

## 1.2 `enterprise`

`enterprise` table has 6 original property: id, name, country, city, supply_center, industry. We use foreign key to connect it with center:

```postgresql
constraint enterprise_supply_center_fk foreign key (supply_center_id) references center (id) on delete cascade
```

## 1.3 `model`

`model` table has 5 original property: id, number, model_name, product_name, unit_price

We add sales to update the number of sales model of each model when we import task1_in_stoke_test_data_publish.csv, task2_test_data_publish.csv, task34_update_test_data_publish.tsv, task34_delete_test_data_publish.tsv.

## 1.4 `staff`

`staff` table has 5 original property:  id, name, age, gender, number, mobile_number, type, supply_center. We use foreign key to connect it with center.

```postgresql
constraint center_staff_fk foreign key (supply_center_id) references center (id) on delete cascade
```

## 1.5 `contract`

`contract` table is a new table and used to record the information of contract in task2_test_data_publish.csv. We use foreign key to connect it with staff

```postgresql
constraint contract_staff_fk foreign key (contract_manager_id) references staff (id) on delete cascade
```

## 1.6 `inventory`

`inventory` table is a new table and used to record the information of stock in task1_in_stoke_test_data_publish.csv, and update it in task2_test_data_publish.csv, task34_update_test_data_publish.tsv,task34_delete_test_data_publish.tsv. We use foreign key to connect it with model and supply center

```postgresql
constraint stock_center_fk foreign key (supply_center_id) references center (id) on delete cascade
constraint stock_model_fk foreign key (product_model_id) references model (id) on delete cascade
```

## 1.7 `center_record`

`center_record` table is a new table and used to record the information of stock in task1_in_stoke_test_data_publish.csv. We use foreign key to connect it with model, supply center and staff

## 1.8 `orders`

`orders` table is a new table and used to record the information of each order in task1_in_stoke_test_data_publish.csv, and update it in task2_test_data_publish.csv, task34_update_test_data_publish.tsv,task34_delete_test_data_publish.tsv. We use foreign key to connect it with model, supply center, staff and contract

```postgresql
constraint orders_product_model_fk foreign key (product_model_id) references model (id) on delete cascade,
constraint orders_salesman_fk foreign key (salesman_id) references staff (id) on delete cascade,
constraint orders_enterprise_fk foreign key (enterprise_id) references enterprise (id) on delete cascade,
constraint orders_contract_fk foreign key (contract_number) references contract (contract_number) on delete cascad
```

# **2. API Design**



# **3. Advanced Part**

## 3.2 Design Pattern

**DAO (Data Access Objects)** is applied into this project to implment enable access to persistent data between business logic and persistent data and wrap all database operations. This design pattern mainly divides the **Java** classes into the following layers:

- `entity`: Used to store and transfer object data.
- `service`: Define all operations on the database as abstract methods, which can provide multiple implementations.
- `impl`: Give a concrete implementation of the **service** interface definition method for different databases.

Besides, since we use **Mybatis-Plus** to implement manipulate and operate **CRUD** of the database in **Java**, and communication and interaction between front and back-end, another layers will be added:

- `mapper`: Composed of **Java** interfaces and **XML** files. It has functions of:
  1. Define the parameter type
  2. Configuration Cache
  3. Provide SQL statements and dynamic SQL
  4. Define the mapping relationship between query results and **POJO**
- `controller`: Responsible for front-end and back-end interaction, accepting front-end requests, calling the service layer, receiving data returned from the service layer, and finally returning the specific page and data to the client.

## 3.3 HTTP/RESTful Web Services

In this project, we use [**Springboot**]([Spring Boot](https://spring.io/projects/spring-boot)) to encapsulate and implement the backend API. To implement request back-end data and operations through web services, all we need is to add anotations in `controller` layer:

- `@RestController`: provide **Restful** style interface return values, or **json** objects.
- `@GetMapping`: Handle **get** requests, which correspond to `select` operation in the database.
- `@PostMapping`: Handle **post** requests (usually to add data), which correspond to `insert` operation in database.
- `@PutMapping`: Handle **post** requests (usually to modify data), which corrsepond to `update` operation in database.
- `@DeleteMapping`: Delete URL mapping, which corrsepond to `delete` operation in database.
- `@RequestParam`: Specify the Request parameter in the HTTP protocol.
- `@RequestBody`: Used to receive data in a **json** string passed from the front-end to the back-end

## 3.4 Frontend Design

In this project, we also implement the front and back-end separation.  We mainly use **[vue](https://github.com/vuejs/core)** and **[element-plus](https://github.com/element-plus/element-plus)** to build the user interface (web page).

<img src="./image/login-page.png" style="zoom:40%;" />

<center>Figure 1. Login page</center>

User need to enter his or her username and password to login the system.

<img src="./image/page-1.png" style="zoom:40%;" />

<center>Figure 2. Basic web page</center>

The data are mainly demonstrated by the form of table. To make the interface moew beautiful, we implement the pagination function of tables by using the **Pagination InnerInterceptor** of **Mybatis-Plus**. For the basic four information tables (`staff`, `center`, `enterprise`, `product`), user can execute **CRUD** operations in web page. 

<img src="./image/page-insert.png" style="zoom:40%;" />

<center>Figure 3. Insert data example</center>

To simplify the operations of user, we add the `Initialization` button to intialize the four baisc tables at one single button. Besides, user can upload the testcase files directly in frontend page.

<img src="./image/page-api.png" style="zoom:40%;" />

