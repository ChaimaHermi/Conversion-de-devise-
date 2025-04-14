FROM openjdk:17
WORKDIR /app

COPY ./src /app/src

RUN javac src/com/currency/shared/CurrencyConverterInterface.java \
    src/com/currency/client/CurrencyConverterClient.java

CMD ["java", "-cp", "src", "com.currency.client.CurrencyConverterClient"]
