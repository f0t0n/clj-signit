(ns org.f0t0n.clj-signit.constants
  (:use [org.f0t0n.clj-signit.utils :only [char-range]])
  (:import (java.security SecureRandom)))


(def ^String auth-header-prefix "HMAC-SHA256")
(def key-chars (->> [[\0 \9] [\a \z] [\A \Z]] (map #(char-range %)) flatten))
(def key-length 32)
(def ^String signature-format
  "{auth_header_prefix} {access_key}:{hmac_hex_digest}"
  "%s %s:%s")
(def ^String hmac-sha256 "HmacSHA256")
(def ^String hmac-sha384 "HmacSHA384")
(def ^String hmac-sha512 "HmacSHA512")
(def ^String utf-8 "UTF8")
(def ^SecureRandom random (SecureRandom.))
