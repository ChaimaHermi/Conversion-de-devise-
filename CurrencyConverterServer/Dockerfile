FROM openjdk:17
WORKDIR /app

COPY ./src /app/src
COPY ./lib /app/lib

RUN javac -cp ".:lib/json-20231013.jar" \
    src/com/currency/shared/CurrencyConverterInterface.java \
    src/com/currency/server/CurrencyConverterImpl.java \
    src/com/currency/server/CurrencyConverterServer.java

EXPOSE 1099

CMD ["java", "-cp", ".:lib/json-20231013.jar:src", "com.currency.server.CurrencyConverterServer"]
