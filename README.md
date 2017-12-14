# Contacts API Clojure SDK

[![Clojars Project](https://img.shields.io/clojars/v/fullcontact/contacts-api-clojure.svg)](https://clojars.org/fullcontact/contacts-api-clojure)
[![Build Status](https://travis-ci.org/fullcontact/contacts-api-clojure.svg?branch=master)](https://travis-ci.org/fullcontact/contacts-api-clojure)

Clojure SDK for [FullContact Contacts API](https://www.fullcontact.com/apps/docs)

### Installation
**NOTE: contacts-api-clojure is not yet available on clojars.**

`[contacts-api-clojure "0.1.0"]`


### Documentation

API Documentation can be found at [https://www.fullcontact.com/apps/docs](https://www.fullcontact.com/apps/docs)

### Usage

##### Getting Started
---

```
(ns com.example.core
    (:require [full.api.contact.account :as a]))

(defn get-account []
    @(a/get- "<access-token>"))
```

#### Tests
---

To run tests:

`./lein midje`


