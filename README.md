# Documentazione del Sistema di Firma Digitale

## 1. Classe RSA (`RSA.java`)

### Funzionalità Principali:
- Generazione chiavi RSA
- Crittografia/decrittografia RSA
- Implementazione algoritmi matematici di supporto

**Metodi Principali:**

#### `euclidean(int e, int phi)`
```java
public int euclidean(int e, int phi)
```
- **Scopo**: Calcola l'inverso modulare usando l'algoritmo di Euclide esteso
- **Parametri**:
  - `e`: Chiave pubblica iniziale
  - `phi`: Valore φ(n) = (p-1)(q-1)

#### `generateKeys()`
```java
public RSAKeys generateKeys()
```
- **Flusso**:
  1. Genera numeri primi p e q
  2. Calcola n = p*q e φ(n)
  3. Trova e coprimo con φ(n)
  4. Calcola d come inverso modulare

## 2. Classe RSAKeys (`RSAKeys.java`)
```java
public class RSAKeys {
    private int d; // Chiave privata
    private int e; // Chiave pubblica
    private int n; // Modulo
}
```

## 3. Classe SignedMessage (`SignedMessage.java`)
```java
public class SignedMessage {
    private String message;
    private int[] signature;
}
```

## 4. Classe DigitalSignature (`DigitalSignature.java`)

### Metodi:
#### `sign()`
```java
public SignedMessage sign(String message, String algorithm, int d, int n)
```
- **Flusso**:
  1. Calcola hash del messaggio
  2. Crittografa l'hash con chiave privata

## 5. Classe Hash (`Hash.java`)
```java
public class Hash {
    public String getDigest(String inputString, String hashAlgorithm)
}
```

## Esempio d'Uso Completo
```java
public class Main {
    public static void main(String[] args) throws Exception {
        RSA rsa = new RSA();
        RSAKeys keys = rsa.generateKeys();
        
        DigitalSignature ds = new DigitalSignature();
        SignedMessage sm = ds.sign("Test", "SHA-256", keys.getD(), keys.getN());
        
        boolean isValid = ds.verifySignature(sm, "SHA-256", keys.getE(), keys.getN());
        System.out.println("Firma valida: " + isValid);
    }
}
```

## Struttura File
```
project-root/
├── src/
│   ├── Hash.java
│   ├── RSA.java
│   ├── RSAKeys.java
│   ├── SignedMessage.java
│   └── DigitalSignature.java
└── README.md
```
---

## Struttura File

**Emanuele Pippa**  
Studente di Informatica presso la Libera Università di Bolzano
Progetto realizzato per approfondire la crittografia e le firme digitali (RSA, SHA-256)
GitHub: [epippa](https://github.com/epippa)
