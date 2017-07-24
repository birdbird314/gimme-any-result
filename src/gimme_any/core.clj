(ns gimme-any.core
  (:require [gimme-any.parse :refer :all]
            [gimme-any.query :refer :all]))

(def result-retrievers
 [{:source "google" :parser parse-google :query-builder google-query}
  {:source "bing" :parser parse-bing :query-builder bing-query}
  {:source "yahoo" :parser parse-yahoo :query-builder yahoo-query}])


(def result-retrievers-functions
  (doall (map 
    (fn [retriever]
	  (comp 
	    #(assoc {:source (:source retriever)} :result %) 
		(:parser retriever) 
		slurp 
		(:query-builder retriever)))
     result-retrievers)))

(defn gimme-any
  [query]
  (let [result (promise)]
    (doall 
	  (map 
	    (fn [[retrieve source]]
		  (future (deliver result {:source source :results (retrieve query)}))) 
	    result-retrievers-functions))
	@result))
	
