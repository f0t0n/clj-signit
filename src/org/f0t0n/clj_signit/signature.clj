(ns org.f0t0n.clj-signit.signature
  (:use [org.f0t0n.clj-signit.utils :only [hex]])
  (:require [org.f0t0n.clj-signit.constants :as const])
  (:import (javax.crypto Mac)
           (javax.crypto.spec SecretKeySpec)))


(defn secret-key-spec
  [^String secret-key ^String algorithm]
  (SecretKeySpec. (.getBytes secret-key const/utf-8) algorithm))


(defn new-hmac
  [^SecretKeySpec hmac-key ^String algorithm]
  (doto (Mac/getInstance algorithm) (.init hmac-key)))


(defn create-digest
  [secret-key message & {:keys [algorithm] :or {algorithm const/hmac-sha256}}]
    (->
      (new-hmac (secret-key-spec secret-key algorithm) algorithm)
      (.doFinal (.getBytes message const/utf-8))
      hex))


(defn create
  [access-key secret-key message
   & {:keys [algorithm auth-header-prefix]
      :or {algorithm const/hmac-sha256
           auth-header-prefix const/auth-header-prefix}}]
  (create-digest secret-key message))


(println (count (create "test" "test" "Hello World!")))
