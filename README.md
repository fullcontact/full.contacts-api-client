# Contacts API Clojure SDK

[![Clojars Project](https://img.shields.io/clojars/v/fullcontact/full.contacts-api-client.svg)](https://clojars.org/fullcontact/full.contacts-api-client)
[![Build Status](https://travis-ci.org/fullcontact/full.contacts-api-client.svg?branch=master)](https://travis-ci.org/fullcontact/full.contacts-api-client)

Clojure SDK for [FullContact Contacts API](https://www.fullcontact.com/apps/docs)

### Installation

`[fullcontact/full.contacts-api-client "0.2.3"]`

### Documentation

API Documentation can be found at [https://www.fullcontact.com/apps/docs](https://www.fullcontact.com/apps/docs)

### Usage

##### Getting Started

---

```
(ns com.example.core
    (:require [full.contacts-api-client.account :as a]))

(defn get-account []
    @(a/get- "<access-token>"))
```

#### Tests

---

To run tests:

`./lein midje`
