(ns org.f0t0n.clj-signit.signature
  (:require [clojure.string :as str])
  (:require [org.f0t0n.clj-signit.constants :as const])
  (:use [org.f0t0n.clj-signit.utils :only [hex]])
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
  (format
    const/signature-format
    auth-header-prefix
    access-key
    (create-digest secret-key message)))


(defn parse
  [signature]
  ; Parses the header value
  ; and returns a list (<auth_header_prefix>, <access_key>, <hmac_hex_digest>).
  (let [[auth-header-prefix access-secret-keys]
        (-> signature str/trim (str/split  #"\s+" 2))]
    (map str/trim
         (into [auth-header-prefix]
               (str/split access-secret-keys #"\s*:\s*")))))


(defn verify
  [hmac-hex-digest secret-key message
   & {:keys [algorithm]
      :or {algorithm const/hmac-sha256}}]
  (= (create-digest secret-key message) hmac-hex-digest))
