# Socket UDP

Instrucciones para la terminal. El proceso se repite para los archivos de la carpeta E6-2.

## Compilar
Se compila la solución y si se codifica de forma correcta, se generan archivos .class de cada archivo .java
```console
your@terminal:~$ cd A6-socketstcp/E6-1
your@terminal:~$ javac *.java
```

## Terminal 2
Se inicia el servidor
```console
your@terminal:~$ cd A6-socketstcp/E6-1
your@terminal:~$ java ServidorTCP
```

## Terminal 3
Se inicia el cliente y se agrega la dirección IP del servidor a conectarse
```console
your@terminal:~$ cd A6-socketstcp/E6-1
your@terminal:~$ java ClienteTCP <<DIRECCION_IP>>
```
