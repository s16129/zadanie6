# zadanie6
Sklep - REST, Hibernate, JavaX

## Postać obiektów JSON

UWAGA! Nowo wysyłane i aktualizowane obiekty nie mają parametru 'id'!

### Produkt
    {
    "id" : 1,
    "name" : "Nazwa produktu",
    "category" : "ram",
    "price" : 1.5
    }

Przykładowy obiekt do testów:

    {
    "name" : "Nazwa produktu",
    "category" : "ram",
    "price" : 1.5
    }

Dopuszczane wartości patrametru ''price'':
    graphics
	  motherboard
	  hdd
	  ram
    
## Adresy URL i metody HTTP

### Produkty
Wszystkie produkty

    GET /products

Metoda pobierania wszystkich produktów umożliwia również stosowanie filtrów pobieranych produktów, poadawnych jako parametry GET:
    name - fragment nazwy produktu
    category - kategoria produktu
    price_min - minimalna cena produktu
    price_max - maksymalna cena produktu
    
Jeden produkt z określonym ID

    GET /products/{id}
    
Utworzenie nowego produktu - należy wysłać prawidłowy obiekt JSON

    POST /products
    
Aktualizacja produktu o określonym ID - należy wysłać prawidłowy obiekt JSON

    PUT /products/{id}
