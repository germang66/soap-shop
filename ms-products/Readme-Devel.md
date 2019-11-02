## Use of API

* create product

```
{
	"title": "my product 2",
	"price": "22.23",
	"description": "this is the description of first product"
}
```

* create catalog

```
{
	"name" : "Catalog1"
}
```

* associate catalog with product

```
curl -i -X PUT -H "Content-Type:text/uri-list" -d "http://localhost:8080/api/products/1" http://localhost:8080/api/catalogs/2/products
```


## TODO

* create product and cataog in the same request
* to create assoation use postman instead of curl
