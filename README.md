# JPOS
Java Pos 

Developer was on Java SE with the Frameword Netbeans IDE
Autor Garyn Carrillo Caballero, desagred master on Computer Sciences

The software should user JDK 1.7 of 64 bits 
the last user version is jdk1.7.0_80 for client jh

DataBase is Microsoft accces


El acceso directo que se vaya a crear debe ser asi:
C:\Windows\System32\java.exe -jar "C:\JPOS\dist\JPOS.jar"
para que pueda cargar la libreria de coneccion de access
si lo ejecutas directamente del .jar generado del proyecto presenta problema


Si mocrosoft access en de 32 bits, el JDK debe ser de la misma arquitectura, igual para el casos de la de 64-bits.


Version 2.11.27

1-F4 de busqueda de articulo muestra el valor unitario a precio de venta
2-La consulta de Inventario valor unitario a costo y a precio de venta maximo
3-Cuando se crea un articulo nuevo automaticamente trae el plu siguiente (maximo plu mas uno)
4- Se adiciona combo para seleccionar el vendedor - pendiente que quede en la factura
5-Add Table JVendedoras
  Documento  Type Numerico
  Nombres  Type Texto
  Apellidos Type Texto
  Telefono Type Texto
  Direccion Type Texto

6- Adicion del campo IdVendedora  en la tabla JCabFactura