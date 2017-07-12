(ns gimme-any.parse)

(defn- parse-fn
  [regex]
  (fn [response]
    (->> response
	  (re-seq regex)
	  (map second))))

(def parse-google 
  (parse-fn #"data-href=\"([^\"]+)\""))

(def parse-bing
  (parse-fn #"<h2>\s*<a href=\"([^\"]+)\" h="))

(def parse-yahoo
  (parse-fn #"<h3[^>]*>\s*<a[^>]* href=\"([^\"]+)\""))



