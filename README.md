ListServices
============

A small tool to list all cryptographic services available to Java.

https://breakingcode.wordpress.com/2011/07/20/quickpost-listing-all-available-java-cryptographic-services/

Example usage:

    $ java -jar ListServices.jar
     KeyFactory:
             1.2.840.113549.1.3.1
             OID.1.2.840.113549.1.1
             1.2.840.113549.1.1
             OID.1.2.840.113549.1.3.1
             1.3.14.3.2.12
             DSA
             DiffieHellman
             RSA
             DH
             1.2.840.10040.4.1

     TransformService:
             INCLUSIVE_WITH_COMMENTS
             ENVELOPED
             (... output omitted for brevity ...)

     $ java -jar ListProviders.jar MessageDigest
     MessageDigest:
             SHA-256
             SHA-512
             SHA
             SHA-384
             SHA1
             MD5
             SHA-1
             MD2

     $
