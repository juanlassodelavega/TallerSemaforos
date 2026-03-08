# Concurrent-and-distributed-programming

Proyecto sencillo para practicar:
1. Sockets
2. Threads
3. Sincronización productor-consumidor

## Requisitos
- Java 8+

## Compilación
```bash
javac -d bin src/*.java
```

## Ejecución
### Ejemplo de sockets
1. Arranca el servidor:
```bash
java -cp bin Server
```
2. En otra terminal, arranca el cliente:
```bash
java -cp bin Cliente
```

### Ejemplo productor-consumidor
```bash
java -cp bin Program
```

## Mejoras incluidas en esta versión
- Uso de `ArrayBlockingQueue` para asegurar acceso thread-safe al buffer.
- Manejo correcto de interrupciones y cierre limpio de hilos.
- `try-with-resources` en cliente y servidor para liberar sockets/streams.
- Validación de entrada y mensajes de error más claros.
